package com.example.tictactoe.contentProviderExample

import android.app.LoaderManager
import android.content.ContentValues
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.CursorLoader
import com.example.tictactoe.R
import android.content.UriMatcher
import android.net.Uri
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_content_provider.*





class ContentProviderActivity: AppCompatActivity(){


    lateinit var cursorAdapter:ContactsCursorAdapter
    var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    var BASEPATH = "contacts"

    val PROVIDER_NAME = "com.example.MyApplication.StudentsProvider"
    val URL = "content://$PROVIDER_NAME/students"
    val CONTENT_URI = Uri.parse(URL)

    var ID = "id"
    var NAME = "name"
    var PHONE = "phone"
    var CONTACTS = 1
    var CONTACTS_ID = 2
    var contentValues = ContentValues()


    init {

        uriMatcher.addURI(PROVIDER_NAME,BASEPATH,CONTACTS)
        uriMatcher.addURI(PROVIDER_NAME,BASEPATH+"#",CONTACTS_ID)

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.example.tictactoe.R.layout.activity_content_provider)

        cursorAdapter = ContactsCursorAdapter(this,null,0)


        btnAdd.setOnClickListener { addData() }
    }

    private fun addData() {
        contentValues.put(NAME,etStudentName.text.toString())
        contentValues.put(PHONE,"999")

        var uri =contentResolver.insert(CONTENT_URI,contentValues)

        Toast.makeText(this,uri.toString(),Toast.LENGTH_SHORT).show()

    }


}