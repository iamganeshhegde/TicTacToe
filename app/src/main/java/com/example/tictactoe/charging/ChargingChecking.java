package com.example.tictactoe.charging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.example.tictactoe.servicesExample.ForegroundService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.example.tictactoe.R;

import java.lang.reflect.Array;

public class ChargingChecking extends AppCompatActivity implements View.OnClickListener {

    Button btnCheckStatus;
    IntentFilter intentFilter;
    int deviceStatus;
    TextView textViewCharging;
    String currentBatteryStatus = "Battery Info";
    String CHANNEL_ID = "ForegroundServiceChannel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_checking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCheckStatus = findViewById(R.id.btnCheckStatus);
        textViewCharging = findViewById(R.id.textViewCharging);

        btnCheckStatus.setOnClickListener(this);

        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


        int[] a = new int[10];
        int[] b = new int[10];

        for (int i = 0, j = 10; i < 10 && j > 0; i++, j--) {
            a[i] = i;
            b[i] = j;
        }

        for (int i = 0; i < a.length; i++) {
            Log.e("before  swap", "" + a[i]);
            Log.e("before  swap", "" + b[i]);

        }
        a = swapMethod(b, b = a);
        for (int i = 0; i < a.length; i++) {
            Log.e("after  swap", "" + a[i]);
            Log.e("after  swap", "" + b[i]);

        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private int[] swapMethod(int[] a, int[] ints) {

        return a;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCheckStatus:

                checkBatteryStatus();

                break;
        }
    }

    private void checkBatteryStatus() {

        startService(new Intent(this, ChargingForegroundService.class));
       /* registerReceiver(broadcastReceiver,intentFilter);  }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

            int batteryLevel = (int) (((float)level/(float) scale)*100.0f);


            createNotificationChannel();

            Notification notification = new NotificationCompat.Builder(ChargingChecking.this,CHANNEL_ID)
            .setContentTitle("Charging Status")
            .setContentText(currentBatteryStatus)
            .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
            .build();

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                NotificationManager mgr=  getSystemService(NotificationManager.class);

                mgr.notify(1,notification);
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){


                textViewCharging.setText(currentBatteryStatus+"Charging at"+batteryLevel);

            }else if(deviceStatus != BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                textViewCharging.setText(currentBatteryStatus+"Not Charging at"+batteryLevel);

            }


        }
    };

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >Build.VERSION_CODES.O){

           NotificationChannel notificationChannel =  new NotificationChannel(CHANNEL_ID,"Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT);

           NotificationManager notificationManager = getSystemService(NotificationManager.class);
           notificationManager.createNotificationChannel(notificationChannel);

        }*/
    }
}
