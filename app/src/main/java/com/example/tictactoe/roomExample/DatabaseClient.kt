package com.example.tictactoe.roomExample

import android.content.Context
import androidx.room.Room

class DatabaseClient(context: Context) {

    var mcontext:Context = context
    lateinit var mInstance:DatabaseClient

    init {

    }

    lateinit var appDatabase:AppDatabase

    init {
        appDatabase = Room.databaseBuilder(context,AppDatabase::class.java,"myToDos").build()
    }

    /*@Synchronized fun getInstance(context: Context): DatabaseClient {
        if(mInstance == null)
        {
            mInstance = DatabaseClient(context)
        }

        return mInstance
    }*/


    companion object{
         var mDatabaseClient: DatabaseClient? = null

        @Synchronized fun getInstance(mCtx: Context): DatabaseClient {
            if (mDatabaseClient == null) {
                mDatabaseClient = DatabaseClient(mCtx)
            }
            return mDatabaseClient as DatabaseClient
        }
    }


    fun getDatabase():AppDatabase{

        return appDatabase
    }
}