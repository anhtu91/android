package org.owntracks.android.ui.invite;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiInviteBinding;
import org.owntracks.android.model.EntrancePosition;
import org.owntracks.android.model.messages.MessageGetFieldName;
import org.owntracks.android.model.messages.MessageGetKeyID;
import org.owntracks.android.model.messages.MessageInvite;
import org.owntracks.android.model.messages.MessageReceiveFieldName;
import org.owntracks.android.model.messages.MessageReceiveKeyID;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.BaseActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import timber.log.Timber;

public class InviteActivity extends BaseActivity<UiInviteBinding, InviteMvvm.ViewModel> implements InviteMvvm.View{

    @Inject
    EventBus eventBus;

    private Button btnSendInvite;
    private Spinner spinnerKeyID;
    private Spinner spinnerFieldName;
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextTextEmailAddress;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TimePickerDialog.OnTimeSetListener onTimeSetListener;
    private ArrayList<String> listKeyID;
    private ArrayList<String> listFieldName;

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
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        btnSendInvite = (Button) findViewById(R.id.btnSendInvite);

        EnableDisableEditText(false, editTextDate);
        EnableDisableEditText(false, editTextTime);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InviteActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(InviteActivity.this, onTimeSetListener, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay+":"+minute;
                editTextTime.setText(time);
            }
        };

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;

                String date = day + "-" + month + "-" + year;
                editTextDate.setText(date);
            }
        };

        spinnerKeyID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String keyIDParkingSpot = parent.getItemAtPosition(position).toString();
                sendRequestToGetFieldName(keyIDParkingSpot);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSendInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDate = editTextDate.getText().toString();
                String strTime = editTextTime.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                Date date = null;
                try {
                    date = (Date) dateFormat.parse(strDate+" "+strTime);
                } catch (ParseException e) {
                    Timber.e("Error while converting datetime "+e.toString());
                }
                long timeStamp = date.getTime()/1000L;

                String email = editTextTextEmailAddress.getText().toString();
                String keyID = spinnerKeyID.getSelectedItem().toString();
                String fieldName = spinnerFieldName.getSelectedItem().toString();

                sendInvite(keyID, fieldName, email, strDate, strTime, timeStamp);
            }
        });

        sendRequestToGetKeyID();

        eventBus.register(this);
    }

    private void sendInvite(String keyID, String fieldName, String email, String strDate, String strTime, long timeStamp){
        MessageInvite messageInvite = new MessageInvite();
        messageInvite.setKeyIDInvite(keyID);
        messageInvite.setFieldNameInvite(fieldName);
        messageInvite.setEmailInvite(email);
        messageInvite.setDateInvite(strDate);
        messageInvite.setTimeInvite(strTime);
        messageInvite.setTst(timeStamp);
        messageProcessor.queueMessageForSending(messageInvite);
    }

    private void sendRequestToGetKeyID(){
        MessageGetKeyID messageGetKeyID = new MessageGetKeyID();
        messageGetKeyID.setInviteType("getKeyID");
        messageProcessor.queueMessageForSending(messageGetKeyID);
    }

    private void sendRequestToGetFieldName(String keyID){
        MessageGetFieldName messageGetFieldName = new MessageGetFieldName();
        messageGetFieldName.setKeyIDInvite(keyID);
        messageProcessor.queueMessageForSending(messageGetFieldName);
    }

    private void EnableDisableEditText(boolean isEnabled, EditText editText) {
        editText.setFocusable(isEnabled);
        editText.setFocusableInTouchMode(isEnabled) ;
        editText.setClickable(isEnabled);
        editText.setLongClickable(isEnabled);
        editText.setCursorVisible(isEnabled) ;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageReceiveKeyID message) {
        listKeyID = message.getListKeyID();
        ArrayAdapter<String> adapterKeyID = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listKeyID);
        adapterKeyID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKeyID.setAdapter(adapterKeyID);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageReceiveFieldName message) {
        listFieldName = message.getListFieldName();
        ArrayAdapter<String> adapterFieldName = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listFieldName);
        adapterFieldName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFieldName.setAdapter(adapterFieldName);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
