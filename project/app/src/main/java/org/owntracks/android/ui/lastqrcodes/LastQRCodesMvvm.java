package org.owntracks.android.ui.lastqrcodes;

import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

import java.util.Collection;

public interface LastQRCodesMvvm {
    interface View extends MvvmView {
        void removeLastQRCodes(ParkplatzModel p);
        void addLastQRCodes(ParkplatzModel p);
        void updateLastQRCodes(ParkplatzModel p);
    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

        //Collection<ParkplatzModel> getAccessCodeForParking();
        //void onParkplatzClick(ParkplatzModel object);
    }
}
