package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_to_do.*

class TodoActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_to_do)


        addFAB.setOnClickListener { startActivity(Intent(this,AddTaskActivity::class.java)) }



        todoRV.layoutManager = GridLayoutManager(this,2)
        todoRV.adapter = ToDoAdapter()


    }
}
