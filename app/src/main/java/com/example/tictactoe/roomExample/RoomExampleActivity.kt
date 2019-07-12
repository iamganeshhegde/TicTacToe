package com.example.tictactoe.roomExample

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoe.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.item_welcome.*


class RoomExampleActivity : AppCompatActivity(), TaskAdapter.deleteSelectedTask {


    lateinit var taskAdapter: TaskAdapter
    lateinit var builder: AlertDialog.Builder

    lateinit var alertDialog: AlertDialog


    override fun onUpdateTak(task: Task, adapterPosition: Int) {

        updateSelectedTask(task, adapterPosition)
    }

    private fun updateSelectedTask(task: Task, adapterPosition: Int) {


        var btnSAve: Button
        var dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, llParent)

        builder = AlertDialog.Builder(this)

        builder.setView(dialogLayout)

        var et_tak = dialogLayout.findViewById<EditText>(R.id.etTaskName)
        var et_desc = dialogLayout.findViewById<EditText>(R.id.etTaskName)
        var et_finish_by = dialogLayout.findViewById<EditText>(R.id.etTaskName)


        task.desc = dialogLayout.findViewById<EditText>(R.id.etDescription).text.toString()
        task.finishBy = dialogLayout.findViewById<EditText>(R.id.etFinishBy).text.toString()


        btnSAve = dialogLayout.findViewById(R.id.btnSave);
        btnSAve.setOnClickListener {


            task.task = et_tak.text.toString()
            task.desc = et_desc.text.toString()
            task.finishBy = et_finish_by.text.toString()
            updateTask(task, adapterPosition)
        }


        alertDialog = builder.create()
        alertDialog.show()


    }


    override fun onDeleteTask(task: Task) {

        deleteSelectedTask(task)
    }

    private fun deleteSelectedTask(task: Task) {

        class DeleteTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {

                DatabaseClient.getInstance(this@RoomExampleActivity)
                    .getDatabase().taskDao().delete(task)

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                Toast.makeText(this@RoomExampleActivity, "Deleted item", Toast.LENGTH_SHORT).show()


                getTasks()
                taskAdapter.upDateList()
            }
        }

        var deleteTask: DeleteTask = DeleteTask()
        deleteTask.execute()
    }


    var taskList = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_task)


        btnAddTask.setOnClickListener { diaplayAddDataDialog() }

//        btnUpdateTak.setOnClickListener { updateTask() }


//        btnSave.setOnClickListener { addToDatabase() }


        getTasks()


        /*var task = Task()

        task.id = 1;
        task.task = "aa"
        task.desc = "vvvv"
        task.finishBy = "111"


        taskList.add(task)*/


    }

    private fun updateTask(task: Task, adapterPosition: Int) {
/*

//        getUpdateObservable()
        Observable.fromCallable { DatabaseClient.getInstance(this@RoomExampleActivity).getDatabase().taskDao().update(task) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : Observer<Task> {

            })
*/


        Observable.fromCallable {
            DatabaseClient.getInstance(this@RoomExampleActivity).getDatabase().taskDao().update(task)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                //                Toast.makeText(this@RoomExampleActivity,"Updated",Toast.LENGTH_SHORT).show()

                taskAdapter.updateElement(task, adapterPosition)
//                taskAdapter.upDateList()
            }


        /*Observable.just(DatabaseClient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                it -> it.getInstance(this).getDatabase().taskDao().update(task)
                object : Observer<Task> {
                    override fun onComplete() {


                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Task) {

                        Toast.makeText(this@RoomExampleActivity,"Updated",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {

                        e.printStackTrace()
                    }

                }
            }*/


//        DatabaseClient.getInstance(this).getDatabase().taskDao().update(task ).

    }

    private fun getUpdateObservable() {


    }

    override fun onPause() {
        super.onPause()

        if (alertDialog != null) {
            alertDialog.dismiss()
        }
    }

    private fun diaplayAddDataDialog() {


        var btnSAve: Button
        var dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, llParent)

        builder = AlertDialog.Builder(this)

        builder.setView(dialogLayout)



        btnSAve = dialogLayout.findViewById(R.id.btnSave);
        btnSAve.setOnClickListener { addToDatabase(dialogLayout) }


        alertDialog = builder.create()
        alertDialog.show()

    }

    private fun getTasks() {

        class GetTask : AsyncTask<Void, Void, List<Task>>() {
            override fun doInBackground(vararg params: Void?): List<Task> {

                var taskList = DatabaseClient.getInstance(this@RoomExampleActivity)
                    .getDatabase().taskDao().getAll()

                return taskList
            }

            override fun onPostExecute(result: List<Task>?) {
                super.onPostExecute(result)

                upDateTakAdapter(result)
            }

            private fun upDateTakAdapter(result: List<Task>?) {

                if (result != null) {
//                    TaskAdapter(result).upDateList()
                    rvTask.layoutManager = LinearLayoutManager(this@RoomExampleActivity)
                    taskAdapter = TaskAdapter(this@RoomExampleActivity, result)
                    rvTask.adapter = taskAdapter
                    rvTask.isNestedScrollingEnabled = false


                }


            }
        }

        var getTask = GetTask()

        getTask.execute()
    }

    private fun addToDatabase(dialogView: View) {


        var et_taskName = dialogView.findViewById<EditText>(R.id.etTaskName)
        var et_Description = dialogView.findViewById<EditText>(R.id.etDescription)
        var et_FinishBy = dialogView.findViewById<EditText>(R.id.etFinishBy)

        if ((et_taskName.text != null) && (et_Description != null) && (et_FinishBy != null)) {
            var saveInDatabase =
                SaveInDatabase(et_taskName.text.toString(), et_Description.text.toString(), et_FinishBy.text.toString())
            saveInDatabase.execute()
        }
    }

    inner class SaveInDatabase(taskName: String, taskDesc: String, taskFinishBy: String) :
        AsyncTask<Void, Void, Void>() {

        var taskName = taskName
        var taskDesc = taskDesc
        var taskFinishBy = taskFinishBy
        override fun doInBackground(vararg params: Void?): Void? {

            var task = Task()

            task.task = taskName
            task.desc = taskDesc
            task.finishBy = taskFinishBy

            DatabaseClient.getInstance(this@RoomExampleActivity).getDatabase().taskDao().insert(task)

            return null

        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()

            finish()
//             startActivity(Intent(applicationContext, MainActivity::class.java))
        }


    }
}