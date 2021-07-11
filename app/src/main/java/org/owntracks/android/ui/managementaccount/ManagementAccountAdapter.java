package org.owntracks.android.ui.managementaccount;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;

import org.owntracks.android.BR;
import org.owntracks.android.R;
import org.owntracks.android.model.ManagementAccountModel;
import org.owntracks.android.ui.base.BaseAdapter;
import org.owntracks.android.ui.base.BaseAdapterItemView;

import java.util.ArrayList;

class ManagementAccountAdapter extends BaseAdapter<ManagementAccountModel> {
    ManagementAccountAdapter(ObservableList item, ClickListener clickListener) {
        super(BaseAdapterItemView.of(BR.managementAccount , R.layout.ui_row_management_acc));
        setItems(item);
        setClickListener(clickListener);
    }

    interface ClickListener extends BaseAdapter.ClickListener<ManagementAccountModel>{
        void onClick(@NonNull ManagementAccountModel object, @NonNull View view, boolean longClick);
    }
}
