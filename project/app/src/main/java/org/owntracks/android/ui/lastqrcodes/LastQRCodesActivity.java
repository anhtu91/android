package org.owntracks.android.ui.lastqrcodes;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiLastQrCodesBinding;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.ui.base.BaseActivity;

import timber.log.Timber;

public class LastQRCodesActivity extends BaseActivity<UiLastQrCodesBinding, LastQRCodesMvvm.ViewModel> implements LastQRCodesMvvm.View, LastQRCodesAdapter.ClickListener{

    private ObservableList<LastQRCodesModel> lastQRCodesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastQRCodesList = new ObservableArrayList<>();

        bindAndAttachContentView(R.layout.ui_last_qr_codes, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new LastQRCodesAdapter(lastQRCodesList, this));
    }

    @Override
    public void onClick(@NonNull LastQRCodesModel object, @NonNull View view, boolean longClick) {
        viewModel.onLastQRCodesClick(object);
    }

    @Override
    public void onResume() {
        super.onResume();
        lastQRCodesList.clear();

        if(lastQRCodesList.addAll(viewModel.getLastQRCodes())){
            Timber.v("Add successful last qr codes");
        }else{
            Timber.v("Add not successful last qr codes");
        }
    }

    @Override
    public void removeLastQRCodes(LastQRCodesModel p) {
        lastQRCodesList.remove(p);
    }

    @Override
    @MainThread
    public void addLastQRCodes(LastQRCodesModel p) {
        lastQRCodesList.add(p);
    }

    @Override
    @MainThread
    public void updateLastQRCodes(LastQRCodesModel p) {

    }
}
