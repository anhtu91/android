package org.owntracks.android.ui.invite;

import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;


public interface InviteMvvm {
    interface View extends MvvmView {

    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

    }
}
