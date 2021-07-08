package org.owntracks.android.ui.managementaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiManagementAccountBinding;
import org.owntracks.android.model.ManagementAccountModel;
import org.owntracks.android.model.messages.MessageGetSelectedParking;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.ui.base.BaseActivity;

import javax.inject.Inject;

import timber.log.Timber;

public class ManagementAccountActivity extends BaseActivity<UiManagementAccountBinding, ManagementAccountMvvm.ViewModel> implements ManagementAccountMvvm.View, ManagementAccountAdapter.ClickListener{

    private ObservableList<ManagementAccountModel> accountList;
    private RecyclerView recyclerView;
    private FloatingActionButton addNewParkingSpot;

    @Inject
    EventBus eventBus;

    @Inject
    MessageProcessor messageProcessor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountList = new ObservableArrayList<>();
        bindAndAttachContentView(R.layout.ui_management_account, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        addNewParkingSpot = findViewById(R.id.btnAddParking);

        addNewParkingSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click add ", Toast.LENGTH_SHORT).show();
            }
        });

        binding.recyclerViewManagementAcc.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewManagementAcc.setAdapter(new ManagementAccountAdapter(accountList, this));

        //recyclerView = (RecyclerView) findViewById(R.id.recyclerViewManagementAcc);

        binding.recyclerViewManagementAcc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!binding.recyclerViewManagementAcc.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    //Refresh
                    sendRequestToGetSelectedParkingSpot();
                }
            }

        });

        sendRequestToGetSelectedParkingSpot();
    }

    private void sendRequestToGetSelectedParkingSpot(){
        MessageGetSelectedParking messageGetSelectedParking = new MessageGetSelectedParking();
        messageGetSelectedParking.setRequestSelectedParking("getSelectedParking");
        messageProcessor.queueMessageForSending(messageGetSelectedParking);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(@NonNull @NotNull ManagementAccountModel object, @NonNull @NotNull View view, boolean longClick) {
        viewModel.onManagementAccountShortClick(object);
    }

    @Override
    public void updateParkingSpot(ManagementAccountModel p) {

    }
}
