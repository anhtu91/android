package org.owntracks.android.model;

import android.graphics.Bitmap;
import android.text.format.Time;

import androidx.databinding.BaseObservable;

import java.util.Date;

public class ParkplatzModel extends BaseObservable implements Comparable<ParkplatzModel> {
    private String jwt; //QRCode or JWT
    private String email;
    private String keyID;
    private String fieldName;
    private String time;
    private String date;
    private int tst;

    public ParkplatzModel(String jwt, String email, String keyID, String fieldName, String time, String date, int tst) {
        this.jwt = jwt;
        this.email = email;
        this.keyID = keyID;
        this.fieldName = fieldName;
        this.time = time;
        this.date = date;
        this.tst = tst;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTst() {
        return tst;
    }

    public void setTst(int tst) {
        this.tst = tst;
    }

    @Override
    public int compareTo(ParkplatzModel o) {
        if(tst == o.tst){
            return 0;
        }else if(tst > o.tst){
            return -1;
        }else{
            return 1;
        }
    }
}
