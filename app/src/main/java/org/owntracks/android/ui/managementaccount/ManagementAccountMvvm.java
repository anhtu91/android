package org.owntracks.android.ui.managementaccount;

import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

public interface ManagementAccountMvvm {
    interface View extends MvvmView {

    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {

    }
}
