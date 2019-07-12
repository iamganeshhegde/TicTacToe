package com.example.tictactoe.launchmodes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_launchmode_c.*

class LaunchModeCActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_launchmode_c)



        launchModeDFromC.setOnClickListener { startActivity(Intent(this,LaunchModeDActivity::class.java)) }

    }

}
