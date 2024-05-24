package com.skupsie.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String,
    val email:String,
    val password:String,
    val isPremium:Boolean = false
    //TODO add last lessons
)
