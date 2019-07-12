package com.example.tictactoe.contentProviderExample

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.tictactoe.rxjavaexample.utils.Constants
import kotlin.IllegalArgumentException
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentUris
import android.database.sqlite.SQLiteQueryBuilder


class ContactsProvider : ContentProvider() {
    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        /**
         * Add a new student record
         */
        val rowID = db?.insert(STUDENTS_TABLE_NAME, "", values)

        /**
         * If record is added successfully
         */
        if (rowID != null) {
            if (rowID > 0) {
                val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
                context!!.contentResolver.notifyChange(_uri, null)
                return _uri
            }
        }

        throw SQLException("Failed to add a record into $uri")
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        var sortOrdernew = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = STUDENTS_TABLE_NAME

        when (uriMatcher.match(uri)) {
            STUDENTS -> qb.setProjectionMap(STUDENTS_PROJECTION_MAP)

            STUDENT_ID -> qb.appendWhere(_ID + "=" + uri.pathSegments[1])
        }
        /**
         * By default sort on student names
         */
        //            sortOrder = NAME
        if (sortOrder == null || sortOrder === "") {
//            sortOrder = NAME
//            sortOrder = sortOrdernew
        }

        val c = qb.query(
            db, projection, selection,
            selectionArgs, null, null, NAME
        )
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context,Constants.DB_NAME,null,Constants.DB_VERSION)

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.writableDatabase
        return if (db == null) false else true    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {


        val count = 0
        when (uriMatcher.match(uri)) {
            STUDENTS -> db?.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);

            STUDENT_ID -> db?.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);

            else -> throw IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {

        var count = 0

        when (uriMatcher.match(uri)) {
            STUDENTS -> if (db != null) {
                count = db!!.delete(STUDENTS_TABLE_NAME, selection, selectionArgs)
            }

            STUDENT_ID ->{ var id = uri.getPathSegments().get(1);
                if (db != null) {
                    count = db!!.delete(STUDENTS_TABLE_NAME,selection,selectionArgs);
                }
            }
            else ->{
                throw IllegalArgumentException("Unknown URI " + uri);
            }
        }

        return count

    }

    override fun getType(uri: Uri): String? {

        when (uriMatcher.match(uri)) {
            /**
             * Get all student records
             */
            STUDENTS -> return "vnd.android.cursor.dir/vnd.example.students"
            /**
             * Get a particular student
             */
            STUDENT_ID -> return "vnd.android.cursor.item/vnd.example.students"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }

    }


    val PROVIDER_NAME = "com.example.MyApplication.StudentsProvider"
    val URL = "content://$PROVIDER_NAME/students"
    val CONTENT_URI = Uri.parse(URL)

    val _ID = "_id"
    val NAME = "name"
    val GRADE = "grade"

    private val STUDENTS_PROJECTION_MAP: HashMap<String, String>? = null

    val STUDENTS = 1
    val STUDENT_ID = 2

    var uriMatcher: UriMatcher

    init {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
    }

    /**
     * Database specific constant declarations
     */

    private var db: SQLiteDatabase? = null
    val DATABASE_NAME = "College"
    val STUDENTS_TABLE_NAME = "students"
    val DATABASE_VERSION = 1
    val CREATE_DB_TABLE = " CREATE TABLE " + STUDENTS_TABLE_NAME +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " +
            " grade TEXT NOT NULL);"

    private inner class DatabaseHelper : SQLiteOpenHelper {
        override fun onCreate(db: SQLiteDatabase?) {

            if (db != null) {
                db.execSQL(CREATE_DB_TABLE)
            };


        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            if (db != null) {
                db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME)

                onCreate(db);
            };
        }

        constructor(
            context: Context,
            DATABASE_NAME: String,
            factory: SQLiteDatabase.CursorFactory?,
            DATABASE_VERSION: Int
        ) : super(context, DATABASE_NAME, null, DATABASE_VERSION) {


        }
    }


    /*var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    var BASEPATH = "contacts"

    var PROVIDER_NAME = "com.example.tictactoe.contentProviderExample"
    var STRING_URL = "content://"+PROVIDER_NAME+"/"+BASEPATH
    var CONTENT_URI = Uri.parse(STRING_URL)
    var ID = "id"
    var NAME = "name"
    var PHONE = "phone"
    var CONTACTS = 1
    var CONTACTS_ID = 2


    init {

        uriMatcher.addURI(PROVIDER_NAME,BASEPATH,CONTACTS)
        uriMatcher.addURI(PROVIDER_NAME,BASEPATH+"#",CONTACTS_ID)
    }

    companion object{

    }



    lateinit var sqLiteDatabase:SQLiteDatabase
    lateinit var dbHelper:DBHelper






    override fun insert(uri: Uri, values: ContentValues?): Uri? {


        var id = sqLiteDatabase.insert(Constants.DB_NAME,null,values)

        if(id>0){
            var uri = ContentUris.withAppendedId(CONTENT_URI,id)
            context.contentResolver.notifyChange(uri,null)
            return uri
        }
        throw  SQLException("Insertion Failed for URI :" + uri);
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        var cursor:Cursor

        when(uriMatcher.match(uri))
        {
            CONTACTS -> cursor = sqLiteDatabase.query(Constants.DB_NAME,Constants.ALL_COLUMNS,selection,null,null,null,Constants.CONTACT_NAME+"ASC")

            else -> throw java.lang.IllegalArgumentException("Unknown uri"+uri)
        }

        cursor.setNotificationUri(context.contentResolver,uri)

        return cursor
    }

    override fun onCreate(): Boolean {
        dbHelper = DBHelper(context)
        sqLiteDatabase = dbHelper.writableDatabase
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {

            var updateCOunt = 0

        when(uriMatcher.match(uri))
        {
            CONTACTS -> sqLiteDatabase.update(Constants.DB_NAME,values,selection,selectionArgs)
            else -> throw IllegalArgumentException("This is an unknown URI"+uri)
        }
        context.contentResolver.notifyChange(uri,null)
        return updateCOunt


    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {

        var deleteCount = 0

        when(uriMatcher.match(uri))
        {
            CONTACTS -> deleteCount = sqLiteDatabase.delete(Constants.DB_NAME,selection,selectionArgs)
            else -> throw IllegalArgumentException("This is an unknown URI"+uri)
        }

        context.contentResolver.notifyChange(uri,null)
        return deleteCount

    }

    override fun getType(uri: Uri): String? {

        when(uriMatcher.match(uri))
        {
            CONTACTS -> return "vnd.android.cursor.dir/contacts"

            else -> throw IllegalArgumentException("This is an unknown URI"+uri)
        }

    }*/
}