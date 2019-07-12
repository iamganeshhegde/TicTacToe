package com.example.tictactoe.tododb.dao

import androidx.room.*
import com.example.tictactoe.tododb.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT *FROM task ")
    fun getAll():List<Task>


    @Insert
    fun insert(taskList:List<Task>)

    @Update
    fun update(taskList: List<Task>)

    @Delete
    fun delete(taskList: List<Task>)
}