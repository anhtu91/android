package org.owntracks.android.ui.parkplatz;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mikepenz.fastadapter.listeners.LongClickEventHook;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiParkplatzBinding;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.BaseActivity;

import timber.log.Timber;

public class ParkplatzActivity extends BaseActivity<UiParkplatzBinding, ParkplatzMvvm.ViewModel> implements ParkplatzMvvm.View, ParkplatzAdapter.ClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindAndAttachContentView(R.layout.ui_parkplatz, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        Timber.v("enter parkplatz activity");
    }


    @Override
    public void onClick(@NonNull ParkplatzModel object, @NonNull View view, boolean longClick) {
        viewModel.onParkplatzClick(object);
    }
}
