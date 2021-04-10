package org.owntracks.android.model;

import android.graphics.Bitmap;

import androidx.databinding.BaseObservable;

import java.util.Date;
import java.util.Timer;

public class ParkplatzModel extends BaseObservable {

    private Date accessDate;
    private Timer accessTime;
    private String keyIDParkplatz;
    private String fieldNameParkplatz;
    private Bitmap accessCode;

    public ParkplatzModel(){

    }
}
