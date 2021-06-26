package org.owntracks.android.ui.invite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import org.owntracks.android.R;
import org.owntracks.android.databinding.UiInviteBinding;
import org.owntracks.android.model.messages.MessageInviteReceive;
import org.owntracks.android.model.messages.MessageInviteSend;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class InviteActivity extends BaseActivity<UiInviteBinding, InviteMvvm.ViewModel> implements InviteMvvm.View{

    private Button btnGetParkingInfo;

    @Inject
    MessageProcessor messageProcessor;

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
                sendRequestToGetKeyID();
            }
        });
    }

    private void sendRequestToGetKeyID(){
        MessageInviteSend messageInviteSend = new MessageInviteSend();
        messageInviteSend.setInviteSend("Invite");
        messageProcessor.queueMessageForSending(messageInviteSend);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
