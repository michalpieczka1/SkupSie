package com.skupsie.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skupsie.data.Lesson
import com.skupsie.data.LessonsRepository
import com.skupsie.data.User
import com.skupsie.data.UserRepository
import com.skupsie.uiStates.LessonUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LessonViewModel(
    private val lessonsRepository: LessonsRepository,
    private val userRepository: UserRepository,
    currentUser: Flow<User?>
):ViewModel() {
    private val _uiState = MutableStateFlow(LessonUiState(isLoading = true, user = currentUser, lessons = emptyList()))
    val uiState: StateFlow<LessonUiState> = _uiState.asStateFlow()

    init{
        getLessons()
    }

    fun updateCurrentUser(id:Int){
        _uiState.update {
            it.copy(user = userRepository.getUserById(id))
        }
    }


    fun getLessons(){
        viewModelScope.launch {
            _uiState.update{ currentState ->
                currentState.copy(isLoading = true)
            }
            _uiState.update { currentState ->
                try {
                    val lessons = lessonsRepository.getLessons()
                    currentState.copy(lessons = lessons)
                } catch (e: IOException) {
                    _uiState.value.copy(error = e.message, isLoading = false)
                } catch (e: HttpException) {
                    _uiState.value.copy(error = e.message, isLoading = false)
                }
            }
        }
    }
}