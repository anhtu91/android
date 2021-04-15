package org.owntracks.android.ui.parkplatz;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import org.owntracks.android.ui.map.MapActivity;

import java.util.Collection;

import javax.inject.Inject;

import timber.log.Timber;

import static org.owntracks.android.ui.map.MapActivity.SHARED_PREFERENCES_QR_CODE;

@PerActivity
public class ParkplatzViewModel extends BaseViewModel<ParkplatzMvvm.View> implements ParkplatzMvvm.ViewModel<ParkplatzMvvm.View>{

    private static SharedPreferences sharedPreferences;

    @Inject
    public ParkplatzViewModel(@AppContext Context context){

    }

    @Override
    public Collection<ParkplatzModel> getAccessCodeForParking() {
        sharedPreferences.getAll();
        return null;
    }

    @Override
    public void onParkplatzClick(ParkplatzModel object) {
        Timber.v("Click parkplatz code...");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.ParkplatzAdded c){
        //TODO: insert, sort
    }
}
