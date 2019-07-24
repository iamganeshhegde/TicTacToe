package com.example.tictactoe.charging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.example.tictactoe.servicesExample.ForegroundService
import com.example.tictactoe.servicesExample.ServicesExampleActivity

class ChargingForegroundService: Service() {


    val CHANNEL_ID = "ForegroundServiceChannel"
    internal var deviceStatus: Int = 0
    internal var currentBatteryStatus = "Battery Info"
    var intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)


    override fun onBind(intent: Intent?): IBinder? {

        return null
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

                val batteryLevel = (level.toFloat() / scale.toFloat() * 100.0f).toInt()


                createNotificationChannel()

                var builder = NotificationCompat.Builder(this@ChargingForegroundService, CHANNEL_ID)

                val notification = builder
                    .setContentTitle("Charging Status")
                    .setContentText(currentBatteryStatus)
                    .setSmallIcon(android.R.drawable.ic_lock_idle_charging).setAutoCancel(false).setOngoing(true)
                    .build()

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    val mgr = getSystemService(NotificationManager::class.java)

                    mgr.notify(1, notification)
                }
                if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING) {

                    val notification =    builder
                        .setContentTitle("Charging Status")
                        .setContentText(currentBatteryStatus + "Charging at" + batteryLevel)
                        .setSmallIcon(android.R.drawable.ic_lock_idle_charging).setAutoCancel(false).setOngoing(true)
                        .build()
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        val mgr = getSystemService(NotificationManager::class.java)

                        mgr.notify(1, notification)
                    }

//                textViewCharging.setText(currentBatteryStatus + "Charging at" + batteryLevel)

                } else if (deviceStatus != BatteryManager.BATTERY_STATUS_NOT_CHARGING) {




                    val notification =    builder
                        .setContentTitle("Charging Status")
                        .setContentText(currentBatteryStatus + "Not Charging at" + batteryLevel)
                        .setSmallIcon(android.R.drawable.ic_lock_idle_charging).setAutoCancel(false).setOngoing(true)
                        .build()
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        val mgr = getSystemService(NotificationManager::class.java)

                        mgr.notify(1, notification)
                    }

//                textViewCharging.setText(currentBatteryStatus + "Not Charging at" + batteryLevel)

                }


            }
        }


        this@ChargingForegroundService.registerReceiver(broadcastReceiver,intentFilter)




/*


        var input = intent?.getStringExtra("inputExtra")

        createNotificationChannel()

        var notificationIntent = Intent(this, ServicesExampleActivity::class.java)

        var pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)


        var notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText(input)
            .setSmallIcon(android.R.drawable.arrow_down_float)
            .setContentIntent(pendingIntent)
            .build()


        startForeground(1,notification)



*/




        return START_NOT_STICKY



    }

    private fun createNotificationChannel() {


        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            var serviceChannel = NotificationChannel(CHANNEL_ID,"Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)


            var manager = getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(serviceChannel)

        }


    }
}