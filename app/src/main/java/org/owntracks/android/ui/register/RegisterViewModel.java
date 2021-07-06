package org.owntracks.android.ui.register;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class RegisterViewModel extends BaseViewModel<RegisterMvvm.View> implements RegisterMvvm.ViewModel<RegisterMvvm.View>{
    private Context currentContext;
    private Preferences preferences;

    @Inject
    public RegisterViewModel(Preferences preferences, @AppContext Context currentContext) {
        this.currentContext = currentContext;
        this.preferences = preferences;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull RegisterMvvm.View view) {
        super.attachView(savedInstanceState, view);
    }

    public String getHost() {
        return preferences.getRegisterHost();
    }

    public void setHost(String host) {
        preferences.setRegisterHost(host);
    }

    public String getPort() {
        return preferences.getRegisterPort();
    }

    public void setPort(String port) {
        preferences.setRegisterPort(port);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.RegisterAdded c){
        //Do nothings. To remove error
    }
}
