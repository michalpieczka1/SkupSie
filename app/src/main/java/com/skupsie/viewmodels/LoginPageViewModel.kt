package com.skupsie.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.skupsie.screens.loginScreens.LoginScreens
import com.skupsie.uiStates.LoginPageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginPageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginPageUiState())
    val uiState:StateFlow<LoginPageUiState> = _uiState.asStateFlow()

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
        val isValid:Boolean = (email.contains('@') && email.isNotEmpty())
        _uiState.update{ currentState ->
            currentState.copy(isEmailValid = isValid)
        }
    }
    private fun isPasswordValid(password: String) {
        val isValid:Boolean = (password.length >= 6 && password.isNotEmpty())
        _uiState.update { currentState ->
            currentState.copy(isPasswordValid = isValid)
        }
    }
    fun forgotPasswordOnClick(navController: NavController) {
        navController.navigate(LoginScreens.ForgotPassword.name)
    }
    fun loginOnClick(navController: NavController){
        isEmailValid(email)
        isPasswordValid(password)

        if(uiState.value.isEmailValid && uiState.value.isPasswordValid){
            navController.navigate(LoginScreens.Login.name)//TODO change to main page
        }
    }
    fun registerOnClick(navController: NavController){
        navController.navigate(LoginScreens.Register.name)
    }

}