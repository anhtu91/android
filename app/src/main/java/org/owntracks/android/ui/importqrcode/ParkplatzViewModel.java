package org.owntracks.android.ui.importqrcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

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
import org.owntracks.android.ui.qrcodepopup.ImportQrCodePopUp;

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

                String keyID = jwtObject.getString(currentContext.getResources().getString(R.string.keyid));
                String fieldName = jwtObject.getString(currentContext.getResources().getString(R.string.fieldname));
                long tst = jwtObject.getLong(currentContext.getResources().getString(R.string.tst));
                String receiverEmail = jwtObject.getString(currentContext.getResources().getString(R.string.receiverEmail));
                String senderUser = jwtObject.getString(currentContext.getResources().getString(R.string.senderUser));

                ParkplatzModel importedQRCode = new ParkplatzModel(jwt, keyID, fieldName, tst, receiverEmail, senderUser);
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
