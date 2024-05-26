package com.skupsie.data

import com.skupsie.network.LessonApiService

interface LessonsRepository {
    suspend fun getLessons(): List<Lesson>
}

class NetworkLessonsRepository(
    private val lessonApiService: LessonApiService
) : LessonsRepository {
    override suspend fun getLessons(): List<Lesson> = lessonApiService.getLessons()

}