package org.owntracks.android.ui.parkplatz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiParkplatzBinding;
import org.owntracks.android.model.ParkplatzModel;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        qrList = new ObservableArrayList<>();
        bindAndAttachContentView(R.layout.ui_parkplatz, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_PERMISSION_CODE);
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);

        FloatingActionButton addParkingAccessCode = findViewById(R.id.floating_action_button);
        addParkingAccessCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.v("Click add qr code image");
                openFileManagerForQRCode();
            }
        });

        Timber.v("Enter Parkplatz activity");

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new ParkplatzAdapter(qrList, this));
    }

    private void openFileManagerForQRCode() {
        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("image/*");
        pickIntent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            this.startActivityForResult(Intent.createChooser(pickIntent, "Select QRCode"), QRCODE_REQUEST);
        } catch (android.content.ActivityNotFoundException e) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == QRCODE_REQUEST) {
            if (data == null || data.getData() == null) {
                Timber.v("The uri is null, probably the user cancelled the image selection process using the back button.");
                return;
            }

            SQLiteForParkplatz sqLiteForParkplatz = new SQLiteForParkplatz(getApplicationContext());

            final Uri uri = data.getData();
            String qrCodeContent = null;

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                if (bitmap == null) {
                    Timber.v("URI is not a bitmap " + uri.toString());
                    return;
                }

                int width = bitmap.getWidth(), height = bitmap.getHeight();
                int[] pixels = new int[width * height];
                bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                LuminanceSource source = new RGBLuminanceSource(width, height, pixels);
                BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
                Reader reader = new MultiFormatReader();
                Result result = reader.decode(bBitmap);
                qrCodeContent = result.getText();

                Timber.v("qrCode content " + qrCodeContent);
                Toast.makeText(this, "Saved QRCode", Toast.LENGTH_SHORT).show();
                inputStream.close();
            }catch (NotFoundException e) {
                Timber.e("Decode exception " + e);
            } catch (FormatException e) {
                Timber.e("Decode exception " + e);
            } catch (ChecksumException e) {
                Timber.e("Decode exception " + e);
            } catch (IOException e) {
                Timber.e("Error " + e + ". Can not open file" + uri.toString());
            } finally {
                if (qrCodeContent != null) {
                    if (sqLiteForParkplatz.insertQRCode(qrCodeContent)) {
                        Timber.e("Insert QRCode successful");
                    } else {
                        Timber.e("Insert QRCode not successful");
                    }
                }
            }
        } else {
            Timber.e("Import QRCode failed");
        }
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
            Log.v("Permission", "Permission " + permission + " already granted ");
        }
    }
    
    @Override
    public void onClick(@NonNull ParkplatzModel object, @NonNull View view, boolean longClick) {
        viewModel.onParkplatzClick(object);
        Timber.v("Click Parkplatz Activity");
        Timber.v("Object value " + object.getJwt());
        ImageView qrCodeImage = (ImageView) findViewById(R.id.qrCodeFromAccessCodeImageView);

        QRCodeWriter writer = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = writer.encode(object.getJwt(), BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            setContentView(R.layout.show_qr_code_from_access_code);
            qrCodeImage.setImageBitmap(bmp);

            qrCodeImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    finish();
                    return true;
                }
            });

        } catch (WriterException e) {
            Timber.e("Error while writing QR Code " + e.toString());
            //e.printStackTrace();
        } catch (Exception e){
            Timber.e("Error "+e.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        qrList.clear();

        if (qrList.addAll(viewModel.getImportedJWTsInfo())) {
            Timber.v("Add successful invited JWTs");
        } else {
            Timber.v("Add not successful invited JWTs");
        }
    }

    @Override
    public void removeAccessCodeForParking(ParkplatzModel p) {
        qrList.remove(p);
    }

    @Override
    @MainThread
    public void addAccessCodeForParking(ParkplatzModel p) {
        qrList.add(p);
        //Collections.sort(mList);
    }

    @Override
    @MainThread
    public void updateAccessCodeForParking(ParkplatzModel p) {
        //Collections.sort(mList);
    }
}
