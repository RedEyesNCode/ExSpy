package com.redeyesncode.xspy.utils;

import android.Manifest;

public class Constants {

    public static final String DEV_XSPY = "DEV_XSPY-->";

    public static final String[] PERMISSION_ARRAY_Q_ANDROID = new String[]{Manifest.permission.CAMERA
            ,Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ,Manifest.permission.READ_CONTACTS
            ,Manifest.permission.WRITE_CONTACTS
            ,Manifest.permission.MANAGE_EXTERNAL_STORAGE
            ,Manifest.permission.READ_CALL_LOG
            ,Manifest.permission.READ_PHONE_NUMBERS
            ,Manifest.permission.READ_SMS};

    public static final String[] PERMISSION_ARRAY_N_ANDROID = new String[]{Manifest.permission.CAMERA
            ,Manifest.permission.READ_CONTACTS
            ,Manifest.permission.READ_CALL_LOG
            ,Manifest.permission.WRITE_CONTACTS
            ,Manifest.permission.READ_SMS};

    public static final String[] PERMISSION_ARRAY_S_ANDROID = new String[]{Manifest.permission.CAMERA
            ,Manifest.permission.READ_CONTACTS
            ,Manifest.permission.WRITE_CONTACTS
            ,Manifest.permission.READ_CALL_LOG
            ,Manifest.permission.HIDE_OVERLAY_WINDOWS
            ,Manifest.permission.READ_SMS};
}
