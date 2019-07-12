package com.example.tictactoe.roomExample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TaskDao {


    @androidx.room.Query("SELECT *FROM task")
    fun getAll():List<Task>

    @Insert
    fun insert(task:Task)

    @Update
    fun update(task:Task)

    @Delete
    fun delete(task: Task)


}