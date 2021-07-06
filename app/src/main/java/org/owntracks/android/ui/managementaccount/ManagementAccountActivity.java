package org.owntracks.android.ui.managementaccount;

import android.os.Bundle;
import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiManagementAccountBinding;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.ui.base.BaseActivity;

import javax.inject.Inject;

public class ManagementAccountActivity extends BaseActivity<UiManagementAccountBinding, ManagementAccountMvvm.ViewModel> implements ManagementAccountMvvm.View{
    @Inject
    EventBus eventBus;

    @Inject
    MessageProcessor messageProcessor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindAndAttachContentView(R.layout.ui_management_account, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
