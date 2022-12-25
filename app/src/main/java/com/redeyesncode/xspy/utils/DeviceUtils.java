package com.redeyesncode.xspy.utils;

import android.os.Build;

import java.util.ArrayList;

public class DeviceUtils {
    public static ArrayList<String> getDeviceInformation(){

        ArrayList<String> deviceInfo = new ArrayList<>();
        deviceInfo.add(Build.SERIAL);
        deviceInfo.add(Build.VERSION.RELEASE);
        deviceInfo.add(Build.FINGERPRINT);
        deviceInfo.add(Build.HOST);
        deviceInfo.add(Build.VERSION.SDK);
        deviceInfo.add(Build.VERSION.INCREMENTAL);
        deviceInfo.add( Build.BRAND);
        deviceInfo.add(Build.MANUFACTURER);
        deviceInfo.add(Build.MODEL);
        deviceInfo.add(Build.USER);

        return deviceInfo;


    }
}
