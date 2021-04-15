package org.owntracks.android.ui.parkplatz;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mikepenz.fastadapter.listeners.LongClickEventHook;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiParkplatzBinding;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.BaseActivity;

import java.util.Collections;
import java.util.Observable;

import timber.log.Timber;

public class ParkplatzActivity extends BaseActivity<UiParkplatzBinding, ParkplatzMvvm.ViewModel> implements ParkplatzMvvm.View, ParkplatzAdapter.ClickListener{

    private ObservableList<ParkplatzModel> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mList = new ObservableArrayList<>();

        bindAndAttachContentView(R.layout.ui_parkplatz, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        Timber.v("enter parkplatz activity");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new ParkplatzAdapter(mList, this));
    }


    @Override
    public void onClick(@NonNull ParkplatzModel object, @NonNull View view, boolean longClick) {
        viewModel.onParkplatzClick(object);
    }

    @Override
    public void onResume() {
        super.onResume();
        mList.clear();
        mList.addAll(viewModel.getAccessCodeForParking());
    }

    @Override
    public void removeAccessCodeForParking(ParkplatzModel p) {
        mList.remove(p);
    }

    @Override
    @MainThread
    public void addAccessCodeForParking(ParkplatzModel p) {
        mList.add(p);
        //Collections.sort(mList);
    }

    @Override
    @MainThread
    public void updateAccessCodeForParking(ParkplatzModel p) {
        //Collections.sort(mList);
    }
}
