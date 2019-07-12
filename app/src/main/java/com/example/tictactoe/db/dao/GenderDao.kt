package com.example.tictactoe.db.dao

import androidx.room.*
import com.example.tictactoe.db.dataclass.Gender

@Dao
interface GenderDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGender(gender: Gender)

    @Update
    fun updateGender(gender:Gender)

    @Delete
    fun deleteGender(gender: Gender)

    @Query("SELECT *FROM Gender WHERE name == :name")
    fun getGenderByName(name:String):List<Gender>

    @Query("SELECT *FROM Gender")
    fun getAllGender():List<Gender>


}