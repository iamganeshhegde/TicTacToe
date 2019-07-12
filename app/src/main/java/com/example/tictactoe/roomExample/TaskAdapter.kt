package com.example.tictactoe.roomExample

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(context:Context,taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {


    lateinit var taskList: List<Task>
    lateinit var deleteSelectedTaskIn: deleteSelectedTask
    var contextThis:Context = context

    init {
        this.taskList = taskList

        deleteSelectedTaskIn = context as deleteSelectedTask
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {

        return taskList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvTask.text = taskList.get(position).task

    }


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun upDateList() {

        Toast.makeText(contextThis,"Updated",Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvTask: TextView


        init {

            tvTask = itemView.taskTV

//            itemView.setOnClickListener { Toast.makeText(itemView.context,"Clicked",Toast.LENGTH_SHORT).show() }

            itemView.setOnLongClickListener {if(it.id == R.id.llItemTak){
                Toast.makeText(it.context, "Clicked" + adapterPosition, Toast.LENGTH_SHORT).show()


                var alertDialogBuilder = AlertDialog.Builder(itemView.context)

                alertDialogBuilder.setTitle("Do you want to change this item?")

                alertDialogBuilder.setPositiveButton("Delete", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                        deleteTask(taskList.get(adapterPosition))
                    }
                }).setNegativeButton("Update", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {


                        updateTask(taskList.get(adapterPosition),adapterPosition)


                    }

                })

                var ad = alertDialogBuilder.create()
                ad.show()


                return@setOnLongClickListener true
            }

                return@setOnLongClickListener false
            }


//            itemView.taskTV.text =
        }




    }

    private fun updateTask(task: Task, adapterPosition: Int) {


        deleteSelectedTaskIn.onUpdateTak(task,adapterPosition)
    }

    private fun deleteTask(task: Task) {

        deleteSelectedTaskIn.onDeleteTask(task)


    }

    fun updateElement(task: Task, adapterPosition: Int) {

        var getTask = taskList.get(adapterPosition)

        getTask.task = task.task
        getTask.desc = task.desc
        getTask.finishBy = task.finishBy


        Toast.makeText(contextThis,"Updated",Toast.LENGTH_SHORT).show()

        notifyDataSetChanged()
//         taskList.get(adapterPosition) = task

    }

    interface deleteSelectedTask{
        fun onDeleteTask(task:Task)
        fun onUpdateTak(task: Task, adapterPosition: Int)
    }

}
