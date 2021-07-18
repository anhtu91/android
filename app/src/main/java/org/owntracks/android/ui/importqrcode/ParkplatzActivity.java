package org.owntracks.android.ui.importqrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiParkplatzBinding;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.support.JWTUtils;
import org.owntracks.android.support.sqlite.SQLiteForParkplatz;
import org.owntracks.android.ui.base.BaseActivity;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;


public class ParkplatzActivity extends BaseActivity<UiParkplatzBinding, ParkplatzMvvm.ViewModel> implements ParkplatzMvvm.View, ParkplatzAdapter.ClickListener {

    private ObservableList<ParkplatzModel> qrList;
    private static final int QRCODE_REQUEST = 111;
    private static final int READ_PERMISSION_CODE = 101;
    private static final int STORAGE_PERMISSION_CODE = 102;
    private FloatingActionButton addParkingAccessCode;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private RecyclerView recyclerViewQRCode;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        qrList = new ObservableArrayList<>();
        bindAndAttachContentView(R.layout.ui_parkplatz, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_PERMISSION_CODE);
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);

        addParkingAccessCode = findViewById(R.id.btnAddQRCode);
        addParkingAccessCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.i("Click add qr code image");
                openFileManagerForQRCode();
            }
        });

        binding.recyclerViewImportQRCode.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewImportQRCode.setAdapter(new ParkplatzAdapter(qrList, this));

        //Swipe left or right to remove last JWT
        simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition(); //Get swipe position
                SQLiteForParkplatz sqLiteForParkplatz = new SQLiteForParkplatz(ParkplatzActivity.this); //Start SQLLite
                ParkplatzModel selectedQRCode = qrList.get(position);
                String swipedJWT = selectedQRCode.getJWT(); //Get string jwt of swiped object
                if(sqLiteForParkplatz.removeJWT(swipedJWT)){ //Remove jwt from SQLLite
                    Toast.makeText(ParkplatzActivity.this, getText(R.string.deleteQRCode), Toast.LENGTH_LONG).show();
                    qrList.remove(position);
                }
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        recyclerViewQRCode = (RecyclerView) findViewById(R.id.recyclerViewImportQRCode);
        itemTouchHelper.attachToRecyclerView(recyclerViewQRCode);
    }

    private void openFileManagerForQRCode() {
        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("image/*");
        pickIntent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            this.startActivityForResult(Intent.createChooser(pickIntent, getText(R.string.selectQRCode)), QRCODE_REQUEST);
        } catch (android.content.ActivityNotFoundException e) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, getText(R.string.installFileManager),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == QRCODE_REQUEST) {
            if (data == null || data.getData() == null) {
                Timber.i("The uri is null, probably the user cancelled the image selection process using the back button.");
                return;
            }

            SQLiteForParkplatz sqLiteForParkplatz = new SQLiteForParkplatz(getApplicationContext());

            final Uri uri = data.getData();
            String jwt = null;
            boolean isJWTCorrectForm = false;

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                if (bitmap == null) {
                    Timber.i("URI is not a bitmap " + uri.toString());
                    return;
                }

                int width = bitmap.getWidth(), height = bitmap.getHeight();
                int[] pixels = new int[width * height];
                bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                LuminanceSource source = new RGBLuminanceSource(width, height, pixels);
                BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
                Reader reader = new MultiFormatReader();
                Result result = reader.decode(bBitmap);
                jwt = result.getText();

                String jwtContent = JWTUtils.decodeJWT(jwt); //Decode JWT
                JSONObject jwtObject = new JSONObject(jwtContent);

                if(jwtObject.has("keyID") && jwtObject.has("fieldName") && jwtObject.has("tst") && jwtObject.has("receiverEmail") && jwtObject.has("senderUser")){
                    isJWTCorrectForm = true;
                    Timber.i("QRCode Content. Sender user: "+jwtObject.getString("senderUser")+", receiver email: " +jwtObject.getString("receiverEmail")+", keyID: "+jwtObject.getString("keyID")+", fieldName: "+jwtObject.getString("fieldName")+", tst: "+jwtObject.getInt("tst"));
                }

                inputStream.close();
            }catch (NotFoundException e) {
                Timber.e("Decode exception " + e);
                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
            } catch (FormatException e) {
                Timber.e("Decode exception " + e);
                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
            } catch (ChecksumException e) {
                Timber.e("Decode exception " + e);
                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Timber.e("Error " + e + ". Can not open file" + uri.toString());
                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Timber.e("Error parse JSON "+e.toString());
                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Timber.e("Error "+e.toString());
                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
            } finally {
                if (jwt != null) {
                    if(isJWTCorrectForm){
                        if(isJwtAlreadyExist(jwt, sqLiteForParkplatz)){
                            Timber.i("QRCode is already existed in database");
                            Toast.makeText(this, getText(R.string.qrCodeAlreadyExist), Toast.LENGTH_SHORT).show();
                        }else{
                            if (sqLiteForParkplatz.insertJWT(jwt)) {
                                Timber.i("Insert QRCode successful");
                                Toast.makeText(this, getText(R.string.saveQRCodeSuccessful), Toast.LENGTH_SHORT).show();
                            } else {
                                Timber.i("Insert QRCode not successful");
                                Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Timber.i("QRCode is not in correct form");
                        Toast.makeText(this, getText(R.string.insertQRCodeNotSuccessful), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            Timber.i("Import QRCode failed");
        }
    }

    private boolean isJwtAlreadyExist(String jwt, SQLiteForParkplatz sqLiteForParkplatz){
        for(String jwtInDatabase: sqLiteForParkplatz.getAllJWTs()){
            if(jwt.equals(jwtInDatabase)){
                return true;
            }
        }
        return false;
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
        } else {
            Timber.i("Permission " + permission + " already granted ");
        }
    }
    
    @Override
    public void onClick(@NonNull ParkplatzModel object, @NonNull View view, boolean longClick) {
        viewModel.onParkplatzShortClick(object);
    }

    @Override
    public void onResume() {
        super.onResume();
        qrList.clear();

        if (qrList.addAll(viewModel.getImportedJWTsInfo())) {
            Timber.i("Add successful invited JWTs");
        } else {
            Timber.i("Add not successful invited JWTs");
        }
    }

    @Override
    public void removeJWT(ParkplatzModel p) {
        qrList.remove(p);
    }

    @Override
    @MainThread
    public void addJWT(ParkplatzModel p) {
        qrList.add(p);
        //Collections.sort(mList);
    }

    @Override
    @MainThread
    public void updateJWT(ParkplatzModel p) {
        //Collections.sort(mList);
    }
}
