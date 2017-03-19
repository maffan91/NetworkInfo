package com.affan.app.networkinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    TextView networkName;
    TextView ip;
    TextView mac;
    TextView imei;
    Button copyClipBoardButton;
    Button refreshButton;
    WifiManager wifiManager;
    WifiInfo wifiInfo;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(connectToInternet()){
            getNetworkInfo();
            Toasty.success(this,"Success!",Toast.LENGTH_SHORT).show();
        }else {

            Toasty.error(this,"No Network Available", Toast.LENGTH_SHORT).show();
        }



    }

    public void getNetworkInfo(){

        networkName.setText(getResources().getString(R.string.wifi_name_label) + " " + wifiInfo.getSSID());
        ip.setText(getResources().getString(R.string.ip_label) + " " + String.valueOf(wifiInfo.getNetworkId()));
        mac.setText(getResources().getString(R.string.mac_label) + " " + wifiInfo.getMacAddress());
        Log.i(TAG, "getNetworkInfo: "+ wifiInfo);

    }


    public boolean connectToInternet(){

        networkName = (TextView) findViewById(R.id.network_name_lable);
        mac = (TextView) findViewById(R.id.mac_label);
        ip = (TextView) findViewById(R.id.ip_label);
        imei = (TextView) findViewById(R.id.imei_label);

        connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        wifiManager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();
        wifiInfo = wifiManager.getConnectionInfo();

        return networkInfo !=null && networkInfo.isConnectedOrConnecting();

    }


}
