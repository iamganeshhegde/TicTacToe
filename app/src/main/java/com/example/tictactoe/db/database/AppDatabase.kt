package com.example.tictactoe.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tictactoe.db.DateTypeConverter
import com.example.tictactoe.db.dao.ExerciseDao
import com.example.tictactoe.db.dao.GenderDao
import com.example.tictactoe.db.dao.RoutineDao
import com.example.tictactoe.db.dao.TraineeDao
import com.example.tictactoe.db.dataclass.Exercise
import com.example.tictactoe.db.dataclass.Gender
import com.example.tictactoe.db.dataclass.Routine
import com.example.tictactoe.db.dataclass.Trainee

@Database(entities = [Exercise::class,Gender::class,Routine::class,Trainee::class],version = 1)
@TypeConverters(DateTypeConverter::class)

abstract class AppDatabase:RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao
    abstract fun genderDao():GenderDao
    abstract fun routineDao ():RoutineDao
    abstract fun traineeDao():TraineeDao


    companion object {
        var INSTANCE: AppDatabase? = null


        fun getAppDatabase(context: Context):AppDatabase?
        {

            if(INSTANCE == null)
            {
                synchronized(AppDatabase::class)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"myDB").build()
                }
            }

            return INSTANCE
        }


        fun destroyDatabase()
        {
            INSTANCE = null
        }

    }
}