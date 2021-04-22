package org.owntracks.android.ui.lastqrcodes;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;

import org.owntracks.android.databinding.UiLastQrCodesBinding;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.BaseAdapter;

public class LastQRCodesActivity extends BaseActivity<UiLastQrCodesBinding, LastQRCodesMvvm.ViewModel> implements LastQRCodesMvvm.View, LastQRCodesAdapter.ClickListener{

    private ObservableList<LastQRCodesModel> lastQRCodesList;

    @Override
    public void onClick(@NonNull Object object, @NonNull View view, boolean longClick) {

    }

    @Override
    public void removeLastQRCodes(ParkplatzModel p) {

    }

    @Override
    public void addLastQRCodes(ParkplatzModel p) {

    }

    @Override
    public void updateLastQRCodes(ParkplatzModel p) {

    }
}
