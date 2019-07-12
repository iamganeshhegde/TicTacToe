package com.example.tictactoe.multithreadingExample

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_multithreading.*
import kotlinx.android.synthetic.main.activity_services_example.*

class MultiThreadingExampleActivity:AppCompatActivity() {


    var handler = Handler()
    var runnable = object : Runnable {
        override fun run() {

            updateTime()
        }
    }

    private fun updateTime() {

        tvDisplaySeconds.text = (Integer.parseInt(tvDisplaySeconds.text.toString()) - 1).toString()

        if(Integer.parseInt(tvDisplaySeconds.text.toString()) == 0)
        {
            btnVisible.visibility = View.VISIBLE

        }else
        {
//            btnVisible.visibility = View.GONE
            handler.postDelayed(runnable,1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_multithreading)

        tvDisplaySeconds.text = "10"


//        handler.postDelayed(runnable,1000)

        btnVisible.setOnClickListener { callMultithreadingHandler() }

    }

    private fun callMultithreadingHandler() {

        tvDisplaySeconds.text = "10"
        btnVisible.visibility = View.GONE

        handler.postDelayed(runnable,1000)

    }


}