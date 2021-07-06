package org.owntracks.android.ui.parkplatz;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;

import org.owntracks.android.BR;
import org.owntracks.android.R;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.BaseAdapter;
import org.owntracks.android.ui.base.BaseAdapterItemView;

class ParkplatzAdapter extends BaseAdapter<ParkplatzModel> {
    ParkplatzAdapter(ObservableList items, ClickListener clickListener) {
        super(BaseAdapterItemView.of(BR.parkplatz, R.layout.ui_row_parkplatz));
        setItems(items);
        setClickListener(clickListener);
    }
    interface ClickListener extends BaseAdapter.ClickListener<ParkplatzModel> {
        void onClick(@NonNull ParkplatzModel object , @NonNull View view, boolean longClick);
    }
}
