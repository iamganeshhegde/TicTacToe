package com.example.tictactoe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false))


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {



    }

    override fun getItemCount(): Int {

        return 10

    }



}
