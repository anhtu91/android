package org.owntracks.android.ui.availableparkingspot;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.R;
import org.owntracks.android.model.CoordinateEntrance;
import org.owntracks.android.model.EntrancePosition;
import org.owntracks.android.model.FieldNameEntranceAndCoordinate;

import java.util.ArrayList;


public class AvailableParkingSpotPopUp extends AppCompatActivity {
    private TextView showInfoAvailableParkingSpot;
    private Spinner spinnerSelectAvailableParking;
    private Spinner spinnerSelectAvailableEntrance;
    private Button btnSubmitSelectedEntrance;
    public static AvailableParkingSpotPopUp instance = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_available_parking_spot);
        spinnerSelectAvailableParking = (Spinner) findViewById(R.id.spinnerSelectParking);
        spinnerSelectAvailableEntrance = (Spinner) findViewById(R.id.spinnerSelectEntrance);
        showInfoAvailableParkingSpot = (TextView) findViewById(R.id.infoAvailableParkingSpot);
        btnSubmitSelectedEntrance = (Button) findViewById(R.id.btnSubmitEntrance);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String keyID = extras.getString("AvailableParkingKeyID");
            String fieldName = extras.getString("AvailableParkingFieldName");
            String time = extras.getString("AvailableParkingTime");
            String date = extras.getString("AvailableParkingDate");
            int numberEntranceAvailableParkingSpot = extras.getInt("NumberEntranceAvailableParking");
            ArrayList<EntrancePosition> entrancePositions = (ArrayList<EntrancePosition>) getIntent().getSerializableExtra("EntrancePosition"); //Get entrance position

            setUpKeyIDSpinner(entrancePositions);

            spinnerSelectAvailableParking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String keyIDParkingSpot = parent.getItemAtPosition(position).toString();
                    setUpFieldNameSpinner(keyIDParkingSpot, entrancePositions);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //PASS
                }
            });

            btnSubmitSelectedEntrance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedEntrance = spinnerSelectAvailableEntrance.getSelectedItem().toString();
                    String selectedParkingSpot = spinnerSelectAvailableParking.getSelectedItem().toString(); //Selected keyID of parking spot

                    ArrayList<Double> selectedPosition = getPositionSelectedEntrance(selectedParkingSpot, selectedEntrance, entrancePositions);
                    EventBus.getDefault().post(new CoordinateEntrance(selectedParkingSpot, selectedEntrance, selectedPosition.get(0), selectedPosition.get(1))); //Longitude, Latitude
                    finish();
                }
            });

            if(numberEntranceAvailableParkingSpot > 0){
                showInfoAvailableParkingSpot.setText("You are in parking spot "+keyID+ " - "+fieldName+"\nat "+time+" on "+date+".\nBut this parking spot is full.\nWe recommend "+entrancePositions.size()+" parking spots in one kilometer around here.\n Select entrance to these parking spot:\n");
            }else{
                showInfoAvailableParkingSpot.setText("You are in parking spot "+keyID+ " - "+fieldName+"\nat "+time+" on "+date+".\nBut this parking spot is full.\nThere is no available parking spot\nin one kilometer around here.\nTouch to close this window.");
            }
        }
        instance = this;
    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }

    private ArrayList<String> getListKeyIDParkingSpot(ArrayList<EntrancePosition> entrancePositions){
        ArrayList<String> listKeyIDAvailableParkingSpot = new ArrayList<String>();
        for(int i=0; i<entrancePositions.size(); i++){
            listKeyIDAvailableParkingSpot.add(entrancePositions.get(i).getKeyIDEntrance());
        }
        return listKeyIDAvailableParkingSpot;
    }

    private void setUpKeyIDSpinner(ArrayList<EntrancePosition> entrancePositions){
        ArrayAdapter<String> adapterKeyIDAvailableParkingSpot = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getListKeyIDParkingSpot(entrancePositions));
        adapterKeyIDAvailableParkingSpot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectAvailableParking.setAdapter(adapterKeyIDAvailableParkingSpot);
    }

    private ArrayList<String> getListSelectedEntrance(String keyIDParkingSpot, ArrayList<EntrancePosition> entrancePositions){
        ArrayList<FieldNameEntranceAndCoordinate> selectedFieldNameEntrance = new ArrayList<FieldNameEntranceAndCoordinate>();
        for(int i=0; i<entrancePositions.size(); i++){
            if(entrancePositions.get(i).getKeyIDEntrance().equals(keyIDParkingSpot)){
                selectedFieldNameEntrance = entrancePositions.get(i).getFieldNameEntranceAndCoordinate();
                break;
            }
        }

        ArrayList<String> listSelectedEntrance = new ArrayList<String>(); //Save entrances of selected parking spot
        for(int i=0; i<selectedFieldNameEntrance.size(); i++){
            listSelectedEntrance.add(selectedFieldNameEntrance.get(i).getFieldNameEntrance());
        }
        return listSelectedEntrance;
    }

    private void setUpFieldNameSpinner(String keyIDParkingSpot, ArrayList<EntrancePosition> entrancePositions){
        ArrayAdapter<String> adapterSelectedEntrance = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, getListSelectedEntrance(keyIDParkingSpot, entrancePositions));
        adapterSelectedEntrance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectAvailableEntrance.setAdapter(adapterSelectedEntrance);
    }

    private ArrayList<Double> getPositionSelectedEntrance(String selectedParkingSpot, String selectedEntrance, ArrayList<EntrancePosition> entrancePositions){
        ArrayList<Double> selectedPosition = new ArrayList<Double>();

        for(EntrancePosition entrance: entrancePositions){
            if(entrance.getKeyIDEntrance().equals(selectedParkingSpot)){
                for(FieldNameEntranceAndCoordinate fieldNameEntranceAndCoordinate: entrance.getFieldNameEntranceAndCoordinate()){
                    if(fieldNameEntranceAndCoordinate.getFieldNameEntrance().equals(selectedEntrance)){
                        selectedPosition = fieldNameEntranceAndCoordinate.getCoordinateEntrance();
                        break;
                    }
                }
                break;
            }
        }

        return selectedPosition;
    }

}
