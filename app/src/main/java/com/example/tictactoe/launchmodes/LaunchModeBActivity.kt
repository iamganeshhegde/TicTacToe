package com.example.tictactoe.launchmodes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_launchmode_b.*

class LaunchModeBActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_launchmode_b)

        launchModeCFromB.setOnClickListener { startActivity(Intent(this,LaunchModeCActivity::class.java)) }


    }

}
