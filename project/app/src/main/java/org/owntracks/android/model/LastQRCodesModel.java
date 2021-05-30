package org.owntracks.android.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

public class LastQRCodesModel extends BaseObservable {
    private String lastJWT; //QRCode or JWT
    private String username;
    private String keyID;
    private String fieldName;
    private String time;
    private String date;

    public LastQRCodesModel(String lastJWT, String username, String keyID, String fieldName, String time, String date) {
        this.lastJWT = lastJWT;
        this.username = username;
        this.keyID = keyID;
        this.fieldName = fieldName;
        this.time = time;
        this.date = date;
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
}
