package com.example.tictactoe.db.dao

import androidx.room.*
import com.example.tictactoe.db.dataclass.Trainee

@Dao
interface TraineeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraineeDetails(trainee:Trainee)

    @Update
    fun updateTraineeDetails(trainee: Trainee)

    @Delete
    fun deleteTraineeDetails(trainee: Trainee)

}