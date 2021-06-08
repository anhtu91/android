package org.owntracks.android.ui.availableparkingspot;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.owntracks.android.R;

import java.util.ArrayList;

public class DisplayAvailableParkingSpot extends AppCompatActivity {
    private TextView showInfoAvailableParkingSpot;

    public static DisplayAvailableParkingSpot instance = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_parking_spot);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String keyID = extras.getString("AvailableParkingKeyID");
            String fieldName = extras.getString("AvailableParkingFieldName");
            String time = extras.getString("AvailableParkingTime");
            String date = extras.getString("AvailableParkingDate");
            int numberAvailableParkingSpot = extras.getInt("NumberAvailableParkingSpots");
            //char[] availableParkingSpot = extras.getCharArray("AvailableParkingSpots"); Not correct type variable

            showInfoAvailableParkingSpot = (TextView) findViewById(R.id.infoAvailableParkingSpot);
            if(numberAvailableParkingSpot > 0){
                showInfoAvailableParkingSpot.setText("You are in parking spot "+keyID+ " - "+fieldName+"\nat "+time+" on "+date+".\nBut this parking spot is full.\nWe recommend "+numberAvailableParkingSpot+" in one kilometer around here.\nTouch to close this window and see another parking spot.");
            }else{
                showInfoAvailableParkingSpot.setText("You are in parking spot "+keyID+ " - "+fieldName+"\nat "+time+" on "+date+".\nBut this parking spot is full.\nThere is no available parking spot in one kilometer around here.\nTouch to close this window.");
            }
        }

        showInfoAvailableParkingSpot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });

        instance = this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }
}
