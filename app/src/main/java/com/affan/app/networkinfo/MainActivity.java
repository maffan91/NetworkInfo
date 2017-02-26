package com.affan.app.networkinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView networkName;
    TextView ip;
    TextView mac;
    TextView imei;
    Button copyClipBoardButton;
    Button refreshButton;
    WifiManager wifiManager;
    WifiInfo wifiInfo;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        getNetworkInfo();

    }

    public void getNetworkInfo(){

        networkName.setText(wifiInfo.getSSID());
        ip.setText(String.valueOf(wifiInfo.getNetworkId()));
        mac.setText(wifiInfo.getMacAddress());


    }


    public void initComponents(){


        networkName = (TextView) findViewById(R.id.network_name_lable);
        mac = (TextView) findViewById(R.id.mac_label);
        ip = (TextView) findViewById(R.id.ip_label);
        imei = (TextView) findViewById(R.id.imei_label);

        connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        wifiManager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);

        wifiInfo = wifiManager.getConnectionInfo();


    }
}
