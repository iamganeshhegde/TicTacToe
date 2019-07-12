package com.example.tictactoe.db.dataclass

import androidx.room.*
import com.example.tictactoe.db.DateTypeConverter
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "traineeRoutine")
@TypeConverters(ListConverter::class)

data class Routine(
    @PrimaryKey(autoGenerate = true)
    val routineId: Int,
    @ColumnInfo(name = "due_day")
    @TypeConverters(DateTypeConverter::class)
    val dueDate:Date,
    val exercise:ArrayList<Exercise>
    ) {
}