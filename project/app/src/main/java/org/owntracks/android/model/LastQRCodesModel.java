package org.owntracks.android.model;

import androidx.databinding.BaseObservable;

public class LastQRCodesModel extends BaseObservable implements Comparable<LastQRCodesModel> {
    private String lastJWT; //QRCode or JWT
    private String username;
    private String keyID;
    private String fieldName;
    private String time;
    private String date;
    private int tst;

    public LastQRCodesModel(String lastJWT, String username, String keyID, String fieldName, String time, String date, int tst) {
        this.lastJWT = lastJWT;
        this.username = username;
        this.keyID = keyID;
        this.fieldName = fieldName;
        this.time = time;
        this.date = date;
        this.tst = tst;
    }

    public int getTst() {
        return tst;
    }

    public void setTst(int tst) {
        this.tst = tst;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastJWT() {
        return lastJWT;
    }

    public String getDateTime(){
        return this.time +" "+ this.date;
    }

    public String getKeyID() {
        return "KeyID: "+keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getFieldName() {
        return "FieldName: "+fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public int compareTo(LastQRCodesModel o) {
        if(tst == o.tst){
            return 0;
        }else if(tst > o.tst){
            return -1;
        }else{
            return 1;
        }
    }
}
