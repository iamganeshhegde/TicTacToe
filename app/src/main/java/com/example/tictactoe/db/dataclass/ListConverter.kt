package com.example.tictactoe.db.dataclass
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class ListConverter {


    @TypeConverter
    fun listToJson(value: List<Exercise>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Exercise>? {

        val objects = Gson().fromJson(value, Array<Exercise>::class.java) as Array<Exercise>
        val list = objects.toList()
        return list
    }
 /*   var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): ArrayList<Exercise>? {

        if (data == null){
//            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Exercise>?): String? {
        return gson.toJson(someObjects)
    }

*/



    /*var gson = Gson()

    @TypeConverter
    fun fromString(data:String?):ArrayList<Exercise>?
    {

        var listType= object :TypeToken<List<Exercise>>(){}.type

        return gson.fromJson(data,listType)
    }


    @TypeConverter
    fun fromArrayLisr(list:ArrayList<Exercise>):String{

        val gson = Gson()
        return gson.toJson(list)
    }*/



        /*@TypeConverter
        fun fromString(value: String): List<String>? {
            val listType = object : TypeToken<List<String>>() {

            }.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: List<String>): String {
            val gson = Gson()
            return gson.toJson(list)
        }*/


}
