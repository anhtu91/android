package org.owntracks.android.model;

import androidx.databinding.BaseObservable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LastQRCodesModel extends BaseObservable implements Comparable<LastQRCodesModel> {
    private String lastJWT;
    private String keyID;
    private String fieldName;
    private long tst;

    public LastQRCodesModel(String lastJWT, String keyID, String fieldName, long tst) {
        this.lastJWT = lastJWT;
        this.keyID = keyID;
        this.fieldName = fieldName;
        this.tst = tst;
    }

    public long getTst() {
        return tst;
    }

    public void setTst(int tst) {
        this.tst = tst;
    }

    public String getLastJWT() {
        return lastJWT;
    }

    public String getDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDateTime = formatter.format(new Date(tst*1000));
        return strDateTime;
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
