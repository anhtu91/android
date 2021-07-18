package org.owntracks.android.model;

import androidx.databinding.BaseObservable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ParkplatzModel extends BaseObservable implements Comparable<ParkplatzModel> {
    private String JWT;
    private String receiverEmail;
    private String senderUser;
    private String keyID;
    private String fieldName;
    private long tst;

    public ParkplatzModel(String JWT, String keyID, String fieldName, long tst, String receiverEmail, String senderUser) {
        this.JWT = JWT;
        this.senderUser = senderUser;
        this.receiverEmail = receiverEmail;
        this.keyID = keyID;
        this.fieldName = fieldName;
        this.tst = tst;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJwt(String JWT) {
        this.JWT = JWT;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
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

    public long getTst() {
        return tst;
    }

    public void setTst(int tst) {
        this.tst = tst;
    }

    public String getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(String senderUser) {
        this.senderUser = senderUser;
    }

    public String getDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        formatter.setTimeZone(TimeZone.getDefault()); //formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String strDateTime = formatter.format(new Date(tst*1000));
        return strDateTime;
    }

    @Override
    public int compareTo(ParkplatzModel o) {
        if(tst == o.tst){
            return 0;
        }else if(tst > o.tst){
            return 1;
        }else{
            return -1;
        }
    }
}
