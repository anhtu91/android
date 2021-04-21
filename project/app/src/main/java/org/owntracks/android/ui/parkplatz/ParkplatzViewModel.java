package org.owntracks.android.ui.parkplatz;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.sqlite.SQLiteDBHelper;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import timber.log.Timber;

import static org.owntracks.android.ui.parkplatz.ParkplatzActivity.SHARED_PREFERENCES_QR_CODE;

@PerActivity
public class ParkplatzViewModel extends BaseViewModel<ParkplatzMvvm.View> implements ParkplatzMvvm.ViewModel<ParkplatzMvvm.View>{

    private static SharedPreferences sharedPreferences;
    private Context currentContext;

    @Inject
    public ParkplatzViewModel(@AppContext Context context){
        this.currentContext =  context;
    }

    @Override
    public Collection<ParkplatzModel> getAccessCodeForParking() {
        //sharedPreferences = currentContext.getSharedPreferences(SHARED_PREFERENCES_QR_CODE, Context.MODE_PRIVATE);

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(currentContext);

        List<ParkplatzModel> qrCodeCollection = new ArrayList<>();

        /*
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for(Map.Entry<String, ?> entry : allEntries.entrySet()){
            qrCodeCollection.add(new ParkplatzModel(entry.getValue().toString()));
            Timber.v("QRCode is "+entry.getValue().toString());
        }
         */
        qrCodeCollection = sqLiteDBHelper.getAllAccessCode();

        Timber.v("Get collections of access code "+qrCodeCollection.toString());

        return qrCodeCollection;
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
