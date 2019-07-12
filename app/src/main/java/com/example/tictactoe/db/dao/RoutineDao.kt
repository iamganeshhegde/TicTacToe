package com.example.tictactoe.db.dao

import androidx.room.*
import com.example.tictactoe.db.dataclass.Routine

@Dao
interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoutine(routine:Routine)

    @Update
    fun updateRoutine(routine:Routine)

    @Delete
    fun deleteRoutine(routine:Routine)
}