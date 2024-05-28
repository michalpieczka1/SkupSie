package com.skupsie.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.skupsie.data.User
import com.skupsie.data.UserRepository
import com.skupsie.screens.loginScreens.LoginScreens
import com.skupsie.uiStates.WelcomeNewUserUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeNewUserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(WelcomeNewUserUiState())
    val uiState = _uiState.asStateFlow()

    var name:String by mutableStateOf("")
        private set

    fun onNameChange(value:String){
        name = value
    }

    fun updateCurrentUser(userId:Int){
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO){
                userRepository.getUserById(userId)
            }
            withContext(Dispatchers.Main){
            _uiState.update { currentState ->
                currentState.copy(currentUser = user.first())
            }
            }
        }

    }

    fun onUserNameCreatorClick(navController:NavController){
            viewModelScope.launch {
                if (name != "") {
                    _uiState.update { currentState ->
                        currentState.copy(currentUser = uiState.value.currentUser?.copy(name = name))
                    }
                    withContext(Dispatchers.IO){
                        uiState.value.currentUser?.let { userRepository.updateUserName(name, it.id) }
                    }
                    withContext(Dispatchers.Main) {
                        navController.navigate("${LoginScreens.Lessons.name}/${uiState.value.currentUser!!.id}") //TODO navigate to main page not login
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(isNameValid = false)
                    }
                }
            }
    }
}