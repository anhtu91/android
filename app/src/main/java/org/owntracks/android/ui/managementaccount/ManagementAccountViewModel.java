package org.owntracks.android.ui.managementaccount;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class ManagementAccountViewModel extends BaseViewModel<ManagementAccountMvvm.View> implements ManagementAccountMvvm.ViewModel<ManagementAccountMvvm.View>{
    private Context currentContext;

    @Inject
    public ManagementAccountViewModel(@AppContext Context currentContext) {
        this.currentContext = currentContext;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull ManagementAccountMvvm.View view) {
        super.attachView(savedInstanceState, view);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.ManagementAccount c){
        //Do nothings. To remove error
    }
}
