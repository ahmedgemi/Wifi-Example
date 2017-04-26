package com.ahmed.wifi_example;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button enableButton,disableButton;
    WifiManager wifi;

    String networkSSID = "Ago";
    String networkPass = "AgO54321";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableButton=(Button)findViewById(R.id.button1);
        disableButton=(Button)findViewById(R.id.button2);

        wifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        List<ScanResult> list2 = wifi.getScanResults();

        for (ScanResult result : list2){
            String data = result.SSID;
            Log.d("test",data);

        }

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";

        conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        conf.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

        conf.preSharedKey = "\""+ networkPass +"\"";


        wifi.addNetwork(conf);

        List<WifiConfiguration> list = wifi.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wifi.disconnect();
                wifi.enableNetwork(i.networkId, true);
                wifi.reconnect();

                wifi.setWifiEnabled(false);
                wifi.setWifiEnabled(true);

                break;
            }
        }

        enableButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                wifi.setWifiEnabled(true);
            }
        });

        disableButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                wifi.setWifiEnabled(false);
            }
        });
    }
}
