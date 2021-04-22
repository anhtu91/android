package org.owntracks.android.ui.lastqrcodes;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;

import org.owntracks.android.BR;
import org.owntracks.android.R;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.ui.base.BaseAdapter;
import org.owntracks.android.ui.base.BaseAdapterItemView;


public class LastQRCodesAdapter extends BaseAdapter<LastQRCodesModel> {
    LastQRCodesAdapter(ObservableList items, ClickListener clickListener) {
        super(BaseAdapterItemView.of(BR.lastQRCodes, R.layout.ui_row_last_qr_codes));
        setItems(items);
        setClickListener(clickListener);
    }
    interface ClickListenter extends BaseAdapter.ClickListener<LastQRCodesModel>{
        @Override
        void onClick(@NonNull LastQRCodesModel object, @NonNull View view, boolean longClick);
    }
}
