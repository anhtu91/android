package org.owntracks.android.ui.managementaccount;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiManagementAccountBinding;
import org.owntracks.android.model.ManagementAccountModel;
import org.owntracks.android.model.SelectedParkingSpot;
import org.owntracks.android.model.messages.MessageDeleteParking;
import org.owntracks.android.model.messages.MessageGetFieldNameAddNewParking;
import org.owntracks.android.model.messages.MessageGetKeyIDAddNewParking;
import org.owntracks.android.model.messages.MessageGetSelectedParking;
import org.owntracks.android.model.messages.MessageReceiveFieldNameAddNewParking;
import org.owntracks.android.model.messages.MessageReceiveKeyIDAddNewParking;
import org.owntracks.android.model.messages.MessageStatusDeleteParkingSpot;
import org.owntracks.android.model.messages.MessageReceiveSelectedParking;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class ManagementAccountActivity extends BaseActivity<UiManagementAccountBinding, ManagementAccountMvvm.ViewModel> implements ManagementAccountMvvm.View, ManagementAccountAdapter.ClickListener{

    @Inject
    EventBus eventBus;

    @Inject
    MessageProcessor messageProcessor;

    private ObservableList<ManagementAccountModel> accountList;
    private ArrayList<String> keyIDList;
    private String correspondingFieldName;
    private FloatingActionButton addNewParkingSpot;
    private ProgressDialog progressDialog;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView recyclerViewManagementAcc;
    private Spinner spinnerKeyID;
    private Spinner spinnerFieldName;
    private boolean clickRefresh = false;

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
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ManagementAccountActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_new_parking_spot, null);
                mBuilder.setTitle(getText(R.string.selectParkingSpot));

                spinnerKeyID = (Spinner) mView.findViewById(R.id.spinnerKeyIDAddParking);
                spinnerFieldName = (Spinner) mView.findViewById(R.id.spinnerFieldNameAddParking);

                ArrayAdapter<String> adapterKeyID = new ArrayAdapter<String>(ManagementAccountActivity.this, android.R.layout.simple_spinner_item, keyIDList);
                adapterKeyID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKeyID.setAdapter(adapterKeyID);

                spinnerKeyID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedKeyID = parent.getItemAtPosition(position).toString();
                        if(!selectedKeyID.equals(getText(R.string.chooseKeyID).toString())){
                            sendRequestToGetAllFieldName(selectedKeyID);
                        }else{
                            //Clear spinnerFieldName
                            spinnerFieldName.setAdapter(null);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //PASS
                    }
                });

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Get selected keyID and fieldname
                        String selectedKeyID = spinnerKeyID.getSelectedItem().toString();
                        String selectedFieldName = spinnerFieldName.getSelectedItem().toString();
                        //Send to server
                        sendRequestToAddNewParking(selectedKeyID, selectedFieldName);
                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        binding.recyclerViewManagementAcc.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewManagementAcc.setAdapter(new ManagementAccountAdapter(accountList, this));

        binding.recyclerViewManagementAcc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!binding.recyclerViewManagementAcc.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    //Refresh
                    clickRefresh = true;
                    sendRequestToGetSelectedParkingSpot();
                    sendRequestToGetAllKeyIDList();
                    activeProgressDialog();
                }
            }
        });

        simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition(); //Get swipe position
                String keyIDDelete = accountList.get(position).getKeyID();
                String fieldNameDelete = accountList.get(position).getFieldName();
                sendRequestToDeleteSelectedParkingSpot(keyIDDelete, fieldNameDelete);
                accountList.remove(position);
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        recyclerViewManagementAcc = (RecyclerView) findViewById(R.id.recyclerViewManagementAcc);
        itemTouchHelper.attachToRecyclerView(recyclerViewManagementAcc);

        //Send request to server
        sendRequestToGetSelectedParkingSpot();
        //Send request KeyID list
        sendRequestToGetAllKeyIDList();
        eventBus.register(this);
    }

    private void activeProgressDialog(){
        progressDialog = new ProgressDialog(ManagementAccountActivity.this);
        progressDialog.setMessage(getText(R.string.wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private void sendRequestToAddNewParking(String keyID, String fieldName){

    }

    private void sendRequestToGetAllKeyIDList(){
        MessageGetKeyIDAddNewParking messageGetKeyIDAddNewParking = new MessageGetKeyIDAddNewParking();
        messageGetKeyIDAddNewParking.setGetKeyIDList("getKeyIDList");
        messageProcessor.queueMessageForSending(messageGetKeyIDAddNewParking);
    }

    private void sendRequestToGetAllFieldName(String selectedKeyID){
        MessageGetFieldNameAddNewParking messageGetFieldNameAddNewParking = new MessageGetFieldNameAddNewParking();
        messageGetFieldNameAddNewParking.setSelectedKeyID(selectedKeyID);
        messageProcessor.queueMessageForSending(messageGetFieldNameAddNewParking);
    }

    private void sendRequestToGetSelectedParkingSpot(){
        MessageGetSelectedParking messageGetSelectedParking = new MessageGetSelectedParking();
        messageGetSelectedParking.setRequestSelectedParking("getSelectedParking");
        messageProcessor.queueMessageForSending(messageGetSelectedParking);
    }

    private void sendRequestToDeleteSelectedParkingSpot(String keyID, String fieldName){
        MessageDeleteParking messageDeleteParking = new MessageDeleteParking();
        messageDeleteParking.setKeyID(keyID);
        messageDeleteParking.setFieldName(fieldName);
        messageProcessor.queueMessageForSending(messageDeleteParking);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageReceiveSelectedParking message) {
        ArrayList<ManagementAccountModel> updateList = new ArrayList<ManagementAccountModel>();
        for(SelectedParkingSpot updateParkingSpot: message.getListSelectedParking()){
            boolean foundParkingSpotInCurrentList = false;
            for(ManagementAccountModel currentParkingSpotInLocal: accountList){
                if(updateParkingSpot.getKeyID().equals(currentParkingSpotInLocal.getKeyID()) && updateParkingSpot.getFieldName().equals(currentParkingSpotInLocal.getFieldName())){
                    foundParkingSpotInCurrentList = true;
                }
            }
            if(!foundParkingSpotInCurrentList){
                updateList.add(new ManagementAccountModel(updateParkingSpot.getKeyID(), updateParkingSpot.getFieldName()));
            }
        }
        accountList.addAll(updateList);
        if(clickRefresh){
            progressDialog.dismiss();
            clickRefresh = false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageStatusDeleteParkingSpot message){
        if(message.getResult()){
            Toast.makeText(getApplicationContext(), getText(R.string.resultDeleteParkingSpotSuccessful), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), getText(R.string.resultDeleteParkingSpotUnsuccessful), Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageReceiveKeyIDAddNewParking message){
        keyIDList = message.getListKeyID();
        keyIDList.add(0, getText(R.string.chooseKeyID).toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageReceiveFieldNameAddNewParking message){
        correspondingFieldName = message.getCorrespondingFieldName();
        ArrayList<String> fieldNameList = new ArrayList<>();
        fieldNameList.add(correspondingFieldName);
        ArrayAdapter<String> adapterFieldName = new ArrayAdapter<String>(ManagementAccountActivity.this, android.R.layout.simple_spinner_item, fieldNameList);
        adapterFieldName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFieldName.setAdapter(adapterFieldName);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(@NonNull @NotNull ManagementAccountModel object, @NonNull @NotNull View view, boolean longClick) {
        //Click parking spot. Still not use for anything
        viewModel.onManagementAccountShortClick(object);
    }
}
