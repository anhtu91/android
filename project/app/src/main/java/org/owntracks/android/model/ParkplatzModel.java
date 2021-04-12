package org.owntracks.android.model;

import android.graphics.Bitmap;
import android.text.format.Time;

import androidx.databinding.BaseObservable;

import java.util.Date;

public class ParkplatzModel extends BaseObservable {

    private Date accessDate;
    private Time accessTime;
    private String keyIDParkplatz;
    private String fieldNameParkplatz;
    private Bitmap accessCode;

    public Date getAccessDate() {
        return accessDate;
    }

    public Time getAccessTime() {
        return accessTime;
    }

    public String getKeyIDParkplatz() {
        return keyIDParkplatz;
    }

    public String getFieldNameParkplatz() {
        return fieldNameParkplatz;
    }

    public Bitmap getAccessCode() {
        return accessCode;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public void setAccessTime(Time accessTime) {
        this.accessTime = accessTime;
    }

    public void setKeyIDParkplatz(String keyIDParkplatz) {
        this.keyIDParkplatz = keyIDParkplatz;
    }

    public void setFieldNameParkplatz(String fieldNameParkplatz) {
        this.fieldNameParkplatz = fieldNameParkplatz;
    }

    public void setAccessCode(Bitmap accessCode) {
        this.accessCode = accessCode;
    }

    public ParkplatzModel(Date accessDate, Time accessTime, String keyIDParkplatz, String fieldNameParkplatz, Bitmap accessCode) {
        this.accessDate = accessDate;
        this.accessTime = accessTime;
        this.keyIDParkplatz = keyIDParkplatz;
        this.fieldNameParkplatz = fieldNameParkplatz;
        this.accessCode = accessCode;
    }
}
