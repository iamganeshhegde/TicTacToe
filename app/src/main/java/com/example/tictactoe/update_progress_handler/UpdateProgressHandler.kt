package com.example.tictactoe.update_progress_handler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_update_progress_handler.*
import kotlin.concurrent.thread

class UpdateProgressHandler : AppCompatActivity() {

    var diffHandler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)


        }
    }


    var handler = Handler {

        tvProgress.text = it?.arg1?.toString()
        return@Handler true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_update_progress_handler)

        btnStartUpdate.setOnClickListener {
            startUpdatingProgress()
        }

        btnBcast.setOnClickListener {
            updateUsingBCast()
        }

    }

    private fun updateUsingBCast() {


    }

    private fun startUpdatingProgress() {

        var count = 0
        var thread = Thread(Runnable {

            while (count < 10) {
                count++
                var message = Message()
                message.arg1 = count
                Thread.sleep(1000)
                handler.sendMessage(message)
            }

        })

        thread.start()

    }
}