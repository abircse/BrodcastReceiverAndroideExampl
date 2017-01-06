package com.example.lolipop.brodcastreceiverandroideexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggleButton = (ToggleButton) findViewById(R.id.wifitoggle);
        checkBox = (CheckBox) findViewById(R.id.brcheckbox);

        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()){
                    wifiManager.setWifiEnabled(true);
                }
                else {
                    wifiManager.setWifiEnabled(false);
                }
            }
        });


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    PackageManager packageManager = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this , NotificationReceiver.class);
                    packageManager.setComponentEnabledSetting(componentName , PackageManager.COMPONENT_ENABLED_STATE_DEFAULT , PackageManager.DONT_KILL_APP);
                    Toast.makeText(MainActivity.this, "Brodcast Receiver Start", Toast.LENGTH_SHORT).show();


                }else {
                    PackageManager packagemanager = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this  , NotificationReceiver.class);
                    packagemanager.setComponentEnabledSetting(componentName , PackageManager.COMPONENT_ENABLED_STATE_DISABLED , PackageManager.DONT_KILL_APP);
                    Toast.makeText(MainActivity.this, "Brodcast Receiver Stopped", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /// if wifi already turned on swith Toggle to on

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()){
            toggleButton.setChecked(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
