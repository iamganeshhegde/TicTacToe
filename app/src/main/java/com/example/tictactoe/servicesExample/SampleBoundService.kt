package com.example.tictactoe.servicesExample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import java.util.*

class SampleBoundService : Service() {

    var localBinder = MyBinder()
    lateinit var chronMeter: Chronometer
    var TAG = SampleBoundService::class.java.name


    override fun onCreate() {
        super.onCreate()

        chronMeter = Chronometer(this)
        chronMeter.base = SystemClock.elapsedRealtime()
        chronMeter.start()
    }


    inner class MyBinder : Binder() {


        fun get():SampleBoundService{
            return this@SampleBoundService
        }
       /* val service: SampleBoundService
            get() = this@SampleBoundService*/
    }

    override fun onBind(intent: Intent?): IBinder? {

        return localBinder

    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)

        Log.i(TAG, "Rebind service")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "Unbind service")


        return super.onUnbind(intent)

    }


    override fun onDestroy() {
        super.onDestroy()

        Log.i(TAG, "Destroy service")
      //  stopSelf()
        chronMeter.stop()
    }

    fun getTimestamp(): String {
        val elapsedMillis = SystemClock.elapsedRealtime() - chronMeter.getBase()
        val hours = (elapsedMillis / 3600000).toInt()
        val minutes = (elapsedMillis - hours * 3600000).toInt() / 60000
        val seconds = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong()).toInt() / 1000
        val millis =
            (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong() - (seconds * 1000).toLong()).toInt()


        var totalTime: String = hours.toString() + ":" + minutes + ":" + seconds + ":" + millis
        Log.i("chron", chronMeter.base.toString());
        return totalTime
    }


    fun randomGenerator(): Int {
        var random = Random()
        var luckyNumber = random.nextInt()

        return luckyNumber
    }
}