package com.redeyesncode.xspy.backend;

public class AndroidLocationModel {

    private Long Latitude;
    private Long Longitude;

    public AndroidLocationModel() {
    }

    public AndroidLocationModel(Long latitude, Long longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public Long getLatitude() {
        return Latitude;
    }

    public void setLatitude(Long latitude) {
        Latitude = latitude;
    }

    public Long getLongitude() {
        return Longitude;
    }

    public void setLongitude(Long longitude) {
        Longitude = longitude;
    }
}
