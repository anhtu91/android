package org.owntracks.android.ui.parkplatz;

import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

import java.util.Collection;

public interface ParkplatzMvvm {
    interface View extends MvvmView {
        void removeAccessCodeForParking(ParkplatzModel p);
        void addAccessCodeForParking(ParkplatzModel p);
        void updateAccessCodeForParking(ParkplatzModel p);
    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

        Collection<ParkplatzModel> getAccessCodeForParking();
        void onParkplatzClick(ParkplatzModel object);
    }
}
