package com.skupsie.network

import com.skupsie.data.Lesson

import retrofit2.http.GET

interface LessonApiService{
    @GET("lessons")
    suspend fun getLessons():List<Lesson>
}
