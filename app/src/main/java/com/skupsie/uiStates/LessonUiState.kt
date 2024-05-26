package com.skupsie.uiStates

import com.skupsie.data.Lesson
import com.skupsie.data.User
import kotlinx.coroutines.flow.Flow

data class LessonUiState(
    val lessons: List<Lesson>,
    val user: Flow<User?>,
    val isLoading: Boolean = false,
    val error: String? = null
)
