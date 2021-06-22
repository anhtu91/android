package org.owntracks.android.ui.parkplatz;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.JWTUtils;
import org.owntracks.android.support.sqlite.SQLiteForParkplatz;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@PerActivity
public class ParkplatzViewModel extends BaseViewModel<ParkplatzMvvm.View> implements ParkplatzMvvm.ViewModel<ParkplatzMvvm.View>{

    private Context currentContext;

    @Inject
    public ParkplatzViewModel(@AppContext Context context){
        this.currentContext =  context;
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

                String email = jwtObject.getString("email");
                String keyID = jwtObject.getString("keyid");
                String fieldName = jwtObject.getString("fieldname");
                String time = jwtObject.getString("time");
                String date = jwtObject.getString("date");
                int tst = jwtObject.getInt("tst");

                ParkplatzModel importedQRCode = new ParkplatzModel(jwt, email, keyID, fieldName, time, date, tst);
                importedQRCodes.add(importedQRCode);
            }
        }catch (Exception e){
            Timber.e("Error while encoding JWT "+e.toString());
        }

        Collections.sort(importedQRCodes);

        return importedQRCodes;
    }

    @Override
    public void onParkplatzClick(ParkplatzModel object) {
        Timber.v("Click parkplatz code...");
        //Generate QRCode from access code JWT
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.ParkplatzAdded c){
        //TODO: insert, sort
    }
}
