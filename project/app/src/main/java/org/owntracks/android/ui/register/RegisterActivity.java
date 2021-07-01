package org.owntracks.android.ui.register;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiRegisterBinding;
import org.owntracks.android.ui.base.BaseActivity;

public class RegisterActivity extends BaseActivity<UiRegisterBinding, RegisterMvvm.ViewModel> implements RegisterMvvm.View{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindAndAttachContentView(R.layout.ui_register, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);
    }
}
