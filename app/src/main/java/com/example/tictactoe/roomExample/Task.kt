package com.example.tictactoe.roomExample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Task:Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    @ColumnInfo(name = "desc")
    lateinit var desc:String

    @ColumnInfo(name = "task")
     var task: String? = null

    @ColumnInfo(name = "finish_by")
     var finishBy: String? = null

    @ColumnInfo(name = "finished")
     var finished: Boolean = false

}