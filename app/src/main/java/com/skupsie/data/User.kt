package com.skupsie.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Entity(tableName = "users")
@TypeConverters(Converters::class)
data class User(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String="",
    val email:String,
    val password:String,
    val isPremium:Boolean = false,
    val latestLessons:List<Lesson> = emptyList()
)
