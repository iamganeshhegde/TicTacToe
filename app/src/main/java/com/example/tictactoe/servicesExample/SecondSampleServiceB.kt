package com.example.tictactoe.servicesExample

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class SecondSampleServiceB: Service() {
    lateinit var mediaPlayer:MediaPlayer
    var TAG = SecondSampleServiceB::class.java.name

    override fun onCreate() {
        super.onCreate()

//        Toast.makeText(this,"Service Created",Toast.LENGTH_SHORT).show()
        Log.i(TAG,"OnCreate Command B: Service Created")
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


//        mediaPlayer = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)
//
//
//        mediaPlayer.isLooping = true
//
//        mediaPlayer.start()
//        Toast.makeText(this,"Service started",Toast.LENGTH_SHORT).show()
        Log.i(TAG," onStartCommand B: Service started")



        //TODo
        return Service.START_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {

        return null

    }


    override fun onDestroy() {
        super.onDestroy()

//        mediaPlayer.stop()
//        Toast.makeText(this,"Service Stopped",Toast.LENGTH_SHORT).show()
        Log.i(TAG,"OnDestroy B:   Service Stopped")


    }
}