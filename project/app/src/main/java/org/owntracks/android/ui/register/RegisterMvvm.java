package org.owntracks.android.ui.register;

import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

public interface RegisterMvvm {
    interface View extends MvvmView{

    }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V>{

    }
}
