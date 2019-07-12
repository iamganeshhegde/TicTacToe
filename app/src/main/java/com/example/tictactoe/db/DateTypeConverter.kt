package com.example.tictactoe.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

class DateTypeConverter {

  /*  @TypeConverter
    fun fromTimeStamp(value: Long?) : Date? {
        return if (value == null) null else Date(value)
    }


    @TypeConverters
    fun dateToTimeStamp(date:Date?): Long? {
        return date?.time
    }
*/

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }


}

