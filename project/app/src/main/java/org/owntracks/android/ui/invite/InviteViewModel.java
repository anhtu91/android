package org.owntracks.android.ui.invite;

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

public class InviteViewModel extends BaseViewModel<InviteMvvm.View> implements InviteMvvm.ViewModel<InviteMvvm.View>{
    private Context currentContext;

    @Inject
    public InviteViewModel(@AppContext Context context){
        this.currentContext = context;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull InviteMvvm.View view) {
        super.attachView(savedInstanceState, view);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.InviteAdded c){
        //TODO: insert, sort
    }
}
