package com.redeyesncode.xspy.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    public void showLog(String message){
        Log.i("DEV_XSPY--->", message);
        Log.i("DEV_XSPY--->", message);
        Log.i("DEV_XSPY--->", message);
        Log.i("DEV_XSPY--->", message);
        Log.i("DEV_XSPY--->", message);
        Log.i("DEV_XSPY--->", message);
    }
}
