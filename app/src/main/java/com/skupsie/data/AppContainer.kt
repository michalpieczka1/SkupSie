package com.skupsie.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skupsie.network.LessonApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val userRepository:UserRepository
    val lessonsRepository:LessonsRepository
}

private const val BASE_URL = "https://my-json-server.typicode.com/michalpieczka1/SkupSieApi/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

val retrofitService : LessonApiService by lazy {
    retrofit.create(LessonApiService::class.java)
}


class AppDataContainer(private val context: Context) : AppContainer {
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(UserDatabase.getDatabase(context).userDao())
    }
    override val lessonsRepository: LessonsRepository by lazy {
        NetworkLessonsRepository(retrofitService)
    }
}