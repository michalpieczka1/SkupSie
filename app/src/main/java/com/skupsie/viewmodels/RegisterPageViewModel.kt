package com.skupsie.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.skupsie.data.UserRepository
import com.skupsie.screens.loginScreens.LoginScreens
import com.skupsie.uiStates.RegisterPageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterPageViewModel (private val userRepository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterPageUiState())
    val uiState: StateFlow<RegisterPageUiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun onEmailChange(input: String){
        email = input
    }
    fun onPasswordChange(input: String){
        password = input
    }



    private fun isEmailValid(email: String) {
        _uiState.update{ currentState ->
            currentState.copy(isEmailValid = email.contains('@')) //TODO exists in database
        }
    }
    private fun isPasswordValid(password: String) {
        _uiState.update { currentState ->
            currentState.copy(isPasswordValid = password.length >= 6)
        }
    }

    fun registerOnClick(navController: NavController){
        isEmailValid(email)
        isPasswordValid(password)

        if(uiState.value.isEmailValid && uiState.value.isPasswordValid){
//            viewModelScope.launch {
//                withContext(Dispatchers.IO){
//                    userRepository.insertUser(
//                        User(name = "", email = email, password = password)
//                    )
//                }
//            }
            navController.navigate(LoginScreens.Login.name)
        }
    }

    fun loginOnClick(navController: NavController){
            navController.navigate(LoginScreens.Login.name)
    }
}