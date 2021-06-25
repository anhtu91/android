package org.owntracks.android.ui.invite;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiInviteBinding;
import org.owntracks.android.model.InviteModel;
import org.owntracks.android.model.ParkplatzModel;
import org.owntracks.android.ui.base.BaseActivity;

import timber.log.Timber;

public class InviteActivity extends BaseActivity<UiInviteBinding, InviteMvvm.ViewModel> implements InviteMvvm.View{

    private Button btnGetParkingInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindAndAttachContentView(R.layout.ui_invite, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        btnGetParkingInfo = (Button) findViewById(R.id.btnGetKeyIDFieldName);

        btnGetParkingInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
