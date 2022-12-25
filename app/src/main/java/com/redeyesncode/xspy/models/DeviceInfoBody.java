package com.redeyesncode.xspy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeviceInfoBody {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deviceInfo")
    @Expose
    private ArrayList<String> deviceInfo = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(ArrayList<String> deviceInfo) {
        this.deviceInfo = deviceInfo;
    }


}
