package com.example.tictactoe.servicesExample

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.sql.Timestamp

class ForegroundService : Service(){

    val CHANNEL_ID = "ForegroundServiceChannel"
    lateinit var mediaPlayer: MediaPlayer
    var TAG = SampleService::class.java.name

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var input = intent?.getStringExtra("inputExtra")

        createNotificationChannel()

        var notificationIntent = Intent(this,ServicesExampleActivity::class.java)

        var pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)


        var notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText(input)
            .setSmallIcon(android.R.drawable.arrow_down_float)
            .setContentIntent(pendingIntent)
            .build()


        startForeground(1,notification)


        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)


        mediaPlayer.isLooping = true

        mediaPlayer.start()
        Toast.makeText(this,"Service started", Toast.LENGTH_SHORT).show()
        Log.i(TAG,"Service started")




        return START_NOT_STICKY

    }

    private fun createNotificationChannel() {


        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            var serviceChannel = NotificationChannel(CHANNEL_ID,"Foreground Service Channel",NotificationManager.IMPORTANCE_DEFAULT)


            var manager = getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(serviceChannel)

        }


    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer.stop()

        var makeText = Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG)


        var toat = Toast(this)
//        toat.

//        makeText.duration = 3;

        var countDownTimer:CountDownTimer = object : CountDownTimer(10000,1000) {
            override fun onFinish() {
                makeText.cancel()
                Log.i(TAG,"Ended "+ (System.currentTimeMillis()/1000).toString())

            }

            override fun onTick(millisUntilFinished: Long) {
                makeText.show()

                Log.i(TAG,"Every single time"+ (System.currentTimeMillis()/1000).toString())

            }

        }


        makeText.show()
        countDownTimer.start()


        Log.i(TAG,"Starrted"+ (System.currentTimeMillis()/1000).toString())


        Log.i(TAG,"Service Stopped")
    }
}