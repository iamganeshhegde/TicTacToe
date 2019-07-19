package com.example.tictactoe.ui_update_service

import android.os.Handler
import android.os.Message


class CustomHandler(appReceiver: AppReciver) : Handler() {
    var appReceiver: AppReciver

    init {

        this.appReceiver = appReceiver
    }

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)


        if (msg != null) {
            appReceiver.onReceiveResult(msg)
        }
    }

    interface AppReciver {
        fun onReceiveResult(message: Message)
    }

}