package com.example.tictactoe.servicesExample

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class SampleIntentService: IntentService("MyService") {

    var TAG = SampleIntentService::class.java.name

    override fun onCreate() {
        super.onCreate()

//        Toast.makeText(this,"Service Created",Toast.LENGTH_SHORT).show()
        Log.i(TAG,"OnCreate Command Intent Service: Service Created")
    }




    override fun onHandleIntent(intent: Intent?) {

        Log.i(TAG,"onHandleIntent Command Intent Service: Service onHandleIntent")
        SystemClock.sleep(10000)
        Log.i(TAG,"onHandleIntent Command Intent Service: Service onHandleIntent completed")


//
//        var message = intent?.getStringExtra("message")
//
//        if (intent != null) {
//            intent.action = ServicesExampleActivity.FILTER_ACTION_KEY
//        }
//
//        SystemClock.sleep(3000)
//
//        var echomessage = "IntentService After a pause of 3 seconds echoes"+message;
//
//
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent!!.putExtra("broadcastMessage",echomessage))

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy Command Intent Service: Service  onDestroyonDestroy")


    }
}