package org.owntracks.android.ui.lastqrcodes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.JWTUtils;
import org.owntracks.android.support.sqlite.SQLiteForLastJWTs;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import org.owntracks.android.ui.qrcode.ImportQrCodePopUp;
import org.owntracks.android.ui.qrcode.QrCodePopUp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Nullable;
import javax.inject.Inject;

import timber.log.Timber;

public class LastQRCodesViewModel extends BaseViewModel<LastQRCodesMvvm.View> implements LastQRCodesMvvm.ViewModel<LastQRCodesMvvm.View>{

    private Context currentContext;

    @Inject
    public LastQRCodesViewModel(@AppContext Context context){
        this.currentContext = context;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull LastQRCodesMvvm.View view) {
        super.attachView(savedInstanceState, view);
    }

    @Override
    public Collection<LastQRCodesModel> getLastQRCodes() {
        SQLiteForLastJWTs sqLiteForLastJWTs = new SQLiteForLastJWTs(currentContext);

        ArrayList<String> lastJWT = new ArrayList<String>();

        lastJWT = sqLiteForLastJWTs.getAllLastJWTs();

        Timber.v("Get collection of last qr code "+lastJWT.toString());

        ArrayList<LastQRCodesModel> lastQRCodes = new ArrayList<LastQRCodesModel>();

        try{
            for(String jwt : lastJWT){
                String jwtContent = JWTUtils.decodeJWT(jwt); //Decode JWT
                JSONObject jwtObject = new JSONObject(jwtContent);

                String username = jwtObject.getString("username");
                String keyID = jwtObject.getString("keyID");
                String fieldName = jwtObject.getString("fieldName");
                String time = jwtObject.getString("time");
                String date = jwtObject.getString("date");
                int tst = jwtObject.getInt("tst");

                LastQRCodesModel lastQRCodesModel1 = new LastQRCodesModel(jwt, username, keyID, fieldName, time, date, tst);
                lastQRCodes.add(lastQRCodesModel1);
            }
        }catch (Exception e){
            Timber.e("Error while encoding JWT "+e.toString());
        }

        Collections.sort(lastQRCodes);
        return lastQRCodes;
    }


    @Override
    public void onLastQRCodesShortClick(LastQRCodesModel object) { //Click short => show QR Code
        Timber.i("Click short "+object.getLastJWT()+" to show QR Code");

        if(QrCodePopUp.instance != null){
            QrCodePopUp.instance.finish();
        }

        if(ImportQrCodePopUp.instance != null){
            ImportQrCodePopUp.instance.finish();
        }

        Intent intent = new Intent(currentContext.getApplicationContext(), QrCodePopUp.class);

        intent.putExtra("JWT", object.getLastJWT()); //Put JWT to intent

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        currentContext.startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.LastQRCodesAdded c){
        //TODO: insert, sort
    }
}
