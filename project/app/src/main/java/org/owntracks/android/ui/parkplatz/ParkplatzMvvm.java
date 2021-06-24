package org.owntracks.android.ui.parkplatz;

import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

import java.util.Collection;

public interface ParkplatzMvvm {
    interface View extends MvvmView {
        void removeJWT(ParkplatzModel p);
        void addJWT(ParkplatzModel p);
        void updateJWT(ParkplatzModel p);
    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

        Collection<ParkplatzModel> getImportedJWTsInfo();
        void onParkplatzShortClick(ParkplatzModel object);
    }
}
