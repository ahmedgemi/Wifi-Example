package com.ahmed.wifi_example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by ahmed on 24/04/17.
 */

public class WifiReceiver extends BroadcastReceiver {

    WifiManager wifi;


    @Override
    public void onReceive(Context context, Intent intent) {

        wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        Toast.makeText(context,"ddddd",Toast.LENGTH_SHORT);

        if (wifi.isWifiEnabled()){
            Toast.makeText(context,"enabled",Toast.LENGTH_SHORT).show();
        }
    }
}
