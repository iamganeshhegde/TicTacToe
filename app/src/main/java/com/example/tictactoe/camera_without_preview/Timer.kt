package com.example.tictactoe.camera_without_preview

import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import java.lang.Exception

class Timer(context:Context,threadHandler:Handler): AsyncTask<Void, Void, Void>() {

    lateinit var threadHandler:Handler
    var context:Context

    init {
        this.threadHandler = threadHandler

        this.context = context
    }

    override fun doInBackground(vararg params: Void?): Void? {

        try{
            Thread.sleep(CameraPreviewActivity.PERIOD)
        }catch (e:Exception)
        {
            e.printStackTrace()
        }

        Message.obtain(threadHandler,CameraPreviewActivity.DONE,"").sendToTarget()

        return null
    }
}