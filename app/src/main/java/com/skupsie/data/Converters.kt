package com.skupsie.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromLessonList(lessons: List<Lesson>): String {
        val gson = Gson()
        return gson.toJson(lessons)
    }

    @TypeConverter
    fun toLessonList(lessonString: String): List<Lesson> {
        val gson = Gson()
        val type = object : TypeToken<List<Lesson>>() {}.type
        return gson.fromJson(lessonString, type)
    }
}