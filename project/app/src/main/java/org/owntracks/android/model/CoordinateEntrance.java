package org.owntracks.android.model;


public class CoordinateEntrance{
    private String keyIDEntrance;
    private String fieldNameEntrance;
    private double Longitude;
    private double Latitude;

    public CoordinateEntrance(String keyIDEntrance, String fieldNameEntrance, double longitude, double latitude) {
        this.keyIDEntrance = keyIDEntrance;
        this.fieldNameEntrance = fieldNameEntrance;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getKeyIDEntrance() {
        return keyIDEntrance;
    }

    public void setKeyIDEntrance(String keyIDEntrance) {
        this.keyIDEntrance = keyIDEntrance;
    }

    public String getFieldNameEntrance() {
        return fieldNameEntrance;
    }

    public void setFieldNameEntrance(String fieldNameEntrance) {
        this.fieldNameEntrance = fieldNameEntrance;
    }


    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLangtitude() {
        return Latitude;
    }

    public void setLangtitude(double langtitude) {
        Latitude = langtitude;
    }
}
