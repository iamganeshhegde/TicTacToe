package com.example.tictactoe.dialog_lifecycle

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_dialog_activity.*
import java.util.*
import android.widget.DatePicker
import android.os.Message


class DialogLifeCycle: AppCompatActivity() {

    private lateinit var  thread: Thread
    private val START_PROGRESS: Int = 100
    private val UPDATE_PROGRESS: Int = 101
    private lateinit var mHandler: Handler
    var TAG = DialogLifeCycle::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG,"onCreate")

        setContentView(com.example.tictactoe.R.layout.activity_dialog_activity)

        progressBar.max = 100

        btnDialogLifeCycleCall.setOnClickListener {
            AlertDialog.Builder(this).setTitle("Hi").setPositiveButton("ok", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    dialog?.dismiss()

                }
            }).create().show()


        }

        var c = Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_MONTH)
        var month = c.get(Calendar.MONTH)
        var year = c.get(Calendar.YEAR)


        val datePickerListener =
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                // this method will call on close dialog box
                year = selectedYear
                month = selectedMonth
                day = selectedDay



                // set selected date into date picker dialog also
                // dpResult.init(year, month, day, null);
            }


        btnDialogDate.setOnClickListener {


            DatePickerDialog(this,datePickerListener,year,month,day).show()
        }


        btnProgress.setOnClickListener {


            mHandler.sendEmptyMessage(START_PROGRESS)

        }


        thread = Thread(object : Runnable {
            override fun run() {

                for(i in 0.. 99){
                    progressBar.progress = i

                    Thread.sleep(1000)


                    var message = Message()

                    message.what = UPDATE_PROGRESS
                    message.arg1 = i
                    mHandler.sendMessage(message)
                }
            }

        })

    }

    override fun onResume() {
        super.onResume()

     /*   mHandler = Handler{

            if(it.what == START_PROGRESS)
                thread.start()
            else if(it.what == UPDATE_PROGRESS)
                tvProgress.setText(it)

        }
*/


        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what === START_PROGRESS) {
                    thread.start()
                } else if (msg.what === UPDATE_PROGRESS) {
                    tvProgress.setText("Count" + msg.arg1)
                }
            }
        }

//        mHandler = Handler();
        Log.i(TAG,"onResume")

    }

    override fun onStart() {
        super.onStart()

        Log.i(TAG,"onStart")

    }

    override fun onStop() {
        super.onStop()

        Log.i(TAG,"onStop")

    }


    override fun onDestroy() {
        super.onDestroy()

        Log.i(TAG,"onDestroy")

    }



    override fun onPause() {
        super.onPause()

        Log.i(TAG,"onPause")
    }

}