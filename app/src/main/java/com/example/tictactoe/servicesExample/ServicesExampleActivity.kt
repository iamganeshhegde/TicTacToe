package com.example.tictactoe.servicesExample

import android.content.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.tictactoe.R
import io.reactivex.internal.subscribers.BoundedSubscriber
import kotlinx.android.synthetic.main.activity_services_example.*

class ServicesExampleActivity: AppCompatActivity() {


     var boundedService: SampleBoundService? = null
    var isBound = false
    var mServiceBound = false


    lateinit var myReceiver:MyReceiver

    inner class MyReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            var message = intent?.getStringExtra("broadcastMessage")

           tvIntentService.setText(tvIntentService.text.toString()+"\n" +message)
//           tvIntentService.setText(tvIntentService.text.toString()+"\n" +message)

        }


    }

    companion object{
       var FILTER_ACTION_KEY = "any_key"
    }

    
    var mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

                mServiceBound = false

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            var myBinder :SampleBoundService.MyBinder = service as SampleBoundService.MyBinder

//            boundedService = myBinder.service
            boundedService = myBinder.get()

            mServiceBound = true

        }
    }
    
    
    

/*
           var serviceConnection = object : ServiceConnection {
               override fun onServiceConnected(name: ComponentName?, service: IBinder?) {


                   var binderBridge : SampleBoundService.MyBinder = service as SampleBoundService.MyBinder

                   boundedService = binderBridge.service

                   isBound = true
               }

               override fun onServiceDisconnected(name: ComponentName?) {

                isBound = false
                boundedService = null




        }


    }*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_services_example)


        btnStartService.setOnClickListener { startSampleService() }
        btnStopService.setOnClickListener { stopSampleService() }

        btnStartServiceB.setOnClickListener { startSampleServiceB() }

        btnStartBoundService.setOnClickListener { startBoundedService() }
        btnStopBoundService.setOnClickListener { stopBoundService() }
        btnIntentService.setOnClickListener { setUpIntentServiceFunctions() }

        btnStartForgroundService.setOnClickListener { setUpForegroundServices() }
        btnStopForgroundService.setOnClickListener { stopForegroundService() }
    }

    private fun startSampleServiceB() {
        startService(Intent(this,SecondSampleServiceB::class.java))


    }

    private fun stopForegroundService() {

        var serviceIntent = Intent(this,ForegroundService::class.java)

        stopService(serviceIntent)
    }

    private fun setUpForegroundServices() {

        var intentService = Intent(this,ForegroundService::class.java)
        intentService.putExtra("inputExtra","Foreground Service example in android")

        ContextCompat.startForegroundService(this,intentService)
    }

    private fun setUpIntentServiceFunctions() {

        var intent = Intent(this,SampleIntentService::class.java)

        intent.putExtra("message",etText.text.toString())

        startService(intent)



    }

    private fun stopBoundService() {

        if(mServiceBound)
        {
            unbindService(mServiceConnection)
            mServiceBound = false

        }

        var intent = Intent(this,SampleBoundService::class.java)
        stopService(intent)
    }

    override fun onStart() {
        super.onStart()


        //broadcast receiver and intent service examples
        setUpReceiver()




        //bounded service example
        var intent = Intent(this,SampleBoundService::class.java)
        startService(intent)
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE)




        /*var intent = Intent(this,SampleBoundService::class.java)
        startService(intent)

        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)*/
    }

    private fun setUpReceiver() {


        myReceiver = MyReceiver()
        var intentFilter = IntentFilter()

        intentFilter.addAction(FILTER_ACTION_KEY)

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,intentFilter)

    }

    override fun onStop() {
        super.onStop()


        //broadcast receivers

        if(myReceiver != null)
        {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
        }


        //bounded service
        if(mServiceBound)
        {
            unbindService(mServiceConnection)
            mServiceBound = false
        }
    }

    private fun startBoundedService() {

        //display
        tvTime.text = boundedService?.getTimestamp() ?: "Not returned"

    }

    override fun onResume() {
        super.onResume()

        /*var runnable:Runnable = Runnable { Toast.makeText(this, boundedService?.randomGenerator().toString(),Toast.LENGTH_SHORT).show()  }

        var handler = Handler()
        handler.postDelayed(runnable,3000)*/


    }


    private fun stopSampleService() {

        stopService(Intent(this,SampleService::class.java))

    }

    private fun startSampleService() {

        startService(Intent(this,SampleService::class.java))

    }
}