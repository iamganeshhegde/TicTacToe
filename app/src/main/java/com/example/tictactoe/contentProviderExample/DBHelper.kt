package com.example.tictactoe.contentProviderExample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tictactoe.rxjavaexample.utils.Constants
import com.example.tictactoe.rxjavaexample.utils.Constants.Companion.CONTACT_CREATED_ON
import com.example.tictactoe.rxjavaexample.utils.Constants.Companion.CONTACT_NAME
import com.example.tictactoe.rxjavaexample.utils.Constants.Companion.CONTACT_PHONE

//class DBHelper(context: Context,dbName:String,DB_VERSION:Int): SQLiteOpenHelper(context,dbName,null,DB_VERSION) {
class DBHelper : SQLiteOpenHelper {

    var DB_NAME = "contacts"
    var DB_VERSION = 1


    constructor(
        context: Context
    ) : super(
        context,
        Constants.DB_NAME,
        null,
        Constants.DB_VERSION
    )

    var CREATE_TABLE = "CREATE TABLE " + DB_NAME + " (" +
            Constants.CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CONTACT_NAME + " TEXT, " +
            CONTACT_PHONE + " TEXT, " +
            CONTACT_CREATED_ON + " TEXT default CURRENT_TIMESTAMP" +
            ")";

    override fun onCreate(db: SQLiteDatabase?) {

        if (db != null) {
            db.execSQL(CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS" + DB_NAME)

            db.execSQL(CREATE_TABLE)
        }
    }
}