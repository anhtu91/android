package org.owntracks.android.ui.invite;

import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

import java.util.Collection;

public interface InviteMvvm {
    interface View extends MvvmView {
        void removeLastQRCodes( p);
        void addLastQRCodes( p);
        void updateLastQRCodes( p);
    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

        Collection<> getLastQRCodes();
        void onLastQRCodesShortClick( object);
    }
}
