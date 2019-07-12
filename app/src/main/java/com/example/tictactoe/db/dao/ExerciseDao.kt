package com.example.tictactoe.db.dao

import androidx.room.*
import com.example.tictactoe.db.dataclass.Exercise
import com.example.tictactoe.db.dataclass.Gender

@Dao
interface ExerciseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Update
    fun updateExercise(exercise: Exercise)

    @Delete
    fun deleteExercise(exercise: Exercise)




}