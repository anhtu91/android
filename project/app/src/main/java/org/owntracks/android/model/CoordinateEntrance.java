package org.owntracks.android.model;


public class CoordinateEntrance{
    private double Longitude;
    private double Latitude;

    public CoordinateEntrance(double longitude, double langtitude) {
        Longitude = longitude;
        Latitude = langtitude;
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
