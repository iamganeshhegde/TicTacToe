package com.example.tictactoe.db.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gender (

    @PrimaryKey(autoGenerate = true)
    val id: Int? =null,
    val name:String

)