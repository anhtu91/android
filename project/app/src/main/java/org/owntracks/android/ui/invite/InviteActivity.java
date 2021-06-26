package org.owntracks.android.ui.invite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiInviteBinding;
import org.owntracks.android.model.EntrancePosition;
import org.owntracks.android.model.messages.MessageGetKeyID;
import org.owntracks.android.model.messages.MessageReceiveKeyID;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class InviteActivity extends BaseActivity<UiInviteBinding, InviteMvvm.ViewModel> implements InviteMvvm.View{

    @Inject
    EventBus eventBus;

    private Button btnGetParkingInfo;
    private Spinner spinnerKeyID;
    private Spinner spinnerFieldName;
    private ArrayList<String> listKeyID;

    @Inject
    MessageProcessor messageProcessor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindAndAttachContentView(R.layout.ui_invite, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        spinnerKeyID = (Spinner) findViewById(R.id.spinnerKeyID);
        spinnerFieldName = (Spinner) findViewById(R.id.spinnerFieldName);

        spinnerKeyID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String keyIDParkingSpot = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendRequestToGetKeyID();

        btnGetParkingInfo = (Button) findViewById(R.id.btnGetKeyIDFieldName);

        btnGetParkingInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        eventBus.register(this);
    }

    private void sendRequestToGetKeyID(){
        MessageGetKeyID messageGetKeyID = new MessageGetKeyID();
        messageGetKeyID.setInviteType("getKeyID");
        messageProcessor.queueMessageForSending(messageGetKeyID);
    }

    private ArrayList<String> sendRequestToGetFieldName(String keyID){
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageReceiveKeyID message) {
        listKeyID = message.getListKeyID();
        ArrayAdapter<String> adapterKeyID = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listKeyID);
        adapterKeyID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKeyID.setAdapter(adapterKeyID);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
