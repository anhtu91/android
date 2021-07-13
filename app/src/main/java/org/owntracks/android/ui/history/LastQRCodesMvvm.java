package org.owntracks.android.ui.history;

import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

import java.util.Collection;

public interface LastQRCodesMvvm {
    interface View extends MvvmView {
        void removeLastQRCodes(LastQRCodesModel p);
        void addLastQRCodes(LastQRCodesModel p);
        void updateLastQRCodes(LastQRCodesModel p);
    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

        Collection<LastQRCodesModel> getLastQRCodes();
        void onLastQRCodesShortClick(LastQRCodesModel object);
    }
}
