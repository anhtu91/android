package org.owntracks.android.ui.parkplatz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.JWTUtils;
import org.owntracks.android.support.sqlite.SQLiteForParkplatz;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import org.owntracks.android.ui.qrcode.ImportQrCodePopUp;
import org.owntracks.android.ui.qrcode.QrCodePopUp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Nullable;
import javax.inject.Inject;

import timber.log.Timber;

@PerActivity
public class ParkplatzViewModel extends BaseViewModel<ParkplatzMvvm.View> implements ParkplatzMvvm.ViewModel<ParkplatzMvvm.View>{

    private Context currentContext;

    @Inject
    public ParkplatzViewModel(@AppContext Context context){
        this.currentContext =  context;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull ParkplatzMvvm.View view) {
        super.attachView(savedInstanceState, view);
    }

    @Override
    public Collection<ParkplatzModel> getImportedJWTsInfo() {
        SQLiteForParkplatz sqLiteForParkplatz = new SQLiteForParkplatz(currentContext);

        ArrayList<String> importedJWTs = new ArrayList<String>();

        importedJWTs = sqLiteForParkplatz.getAllJWTs();

        Timber.v("Get collections of imported QRCode "+importedJWTs.toString());

        ArrayList<ParkplatzModel> importedQRCodes = new ArrayList<ParkplatzModel>();

        try{
            for(String jwt : importedJWTs){
                String jwtContent = JWTUtils.decodeJWT(jwt); //Decode JWT
                JSONObject jwtObject = new JSONObject(jwtContent);

                String keyID = jwtObject.getString("keyID");
                String fieldName = jwtObject.getString("fieldName");
                String date = jwtObject.getString("date");
                String time = jwtObject.getString("time");
                int tst = jwtObject.getInt("tst");
                String receiverEmail = jwtObject.getString("receiverEmail");
                String senderUser = jwtObject.getString("senderUser");

                ParkplatzModel importedQRCode = new ParkplatzModel(jwt, keyID, fieldName, date, time, tst, receiverEmail, senderUser);
                importedQRCodes.add(importedQRCode);
            }
        }catch (Exception e){
            Timber.e("Error while encoding JWT "+e.toString());
        }

        Collections.sort(importedQRCodes);
        return importedQRCodes;
    }

    @Override
    public void onParkplatzShortClick(ParkplatzModel object) {
        Timber.v("Click imported JWT...");

        Intent intent = new Intent(currentContext.getApplicationContext(), ImportQrCodePopUp.class);

        intent.putExtra("JWT", object.getJWT()); //Put JWT to intent

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        currentContext.startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.ParkplatzAdded c){
        //TODO: insert, sort
    }
}
