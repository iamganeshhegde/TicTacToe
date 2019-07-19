//package com.example.tictactoe.ui_update_service
//
//import android.app.IntentService
//import android.content.Intent
//import android.os.Handler
//import android.os.Message
//import android.os.Parcelable
//
//
//class HandlerUIUpdateService:IntentService("HandlerUIUpdateService") {
//
//    var SERVICE_RUNNING = 0
//    var SERVICE_FINISHED = 1
//    var SERVICE_ERROR = 2
//
//    override fun onHandleIntent(intent: Intent?) {
//        val handler: Handler? = intent?.getParcelableExtra<Parcelable>("handler") as Handler
//
//
//        var message:Message = Message()
//
//        message.obj = "Sending message to object after completion of background task"
//
//
//        message.what = SERVICE_FINISHED
//
//        if (handler != null) {
//            handler.sendMessage(message)
//        }
//
//
//
//    }
//}