package org.owntracks.android.model;

import androidx.databinding.BaseObservable;


public class ParkplatzModel extends BaseObservable implements Comparable<ParkplatzModel> {
    private String JWT; //QRCode or JWT
    private String receiverEmail;
    private String senderUser;
    private String keyID;
    private String fieldName;
    private String date;
    private String time;
    private int tst;

    public ParkplatzModel(String JWT, String keyID, String fieldName, String date, String time, int tst, String receiverEmail, String senderUser) {
        this.JWT = JWT;
        this.senderUser = senderUser;
        this.receiverEmail = receiverEmail;
        this.keyID = keyID;
        this.fieldName = fieldName;
        this.date = date;
        this.time = time;
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
        return "KeyID: "+ keyID;
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

    public int getTst() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateTime(){
        return this.time +" "+this.date;
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
