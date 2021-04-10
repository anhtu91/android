package org.owntracks.android.ui.parkplatz;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiParkplatzBinding;
import org.owntracks.android.ui.base.BaseActivity;

import timber.log.Timber;

public class ParkplatzActivity extends BaseActivity<UiParkplatzBinding, ParkplatzMvvm.ViewModel> implements ParkplatzMvvm.View{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindAndAttachContentView(R.layout.ui_parkplatz, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        Timber.v("enter parkplatz activity");
    }
}
