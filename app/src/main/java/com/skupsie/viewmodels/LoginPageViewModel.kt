package com.skupsie.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
        isEmailValid(email)
    }
    fun onPasswordChange(input: String){
        password = input
        isPasswordValid(password)
    }

    private fun isEmailValid(email: String) {
        _uiState.value = LoginPageUiState(isEmailValid = email.contains('@') )
    }
    private fun isPasswordValid(password: String) {
        _uiState.value = LoginPageUiState(isPasswordValid = password.contains(regex =
        Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}\$"))
        )
    }

    private val testTagDeleteLater = "michal" //TODO Delete later
    fun forgotPasswordOnClick(){
        //TODO NAV
        _uiState.value = LoginPageUiState(forgotPasswordOnClick = { Log.d(testTagDeleteLater,"Forgot password clicked")})
    }
    fun loginOnClick(){
        //TODO NAV
        _uiState.value = LoginPageUiState(loginOnClick = { Log.d(testTagDeleteLater,"Login clicked")})
    }
    fun registerOnClick(){
        //TODO NAV
        _uiState.value = LoginPageUiState(registerOnClick = { Log.d(testTagDeleteLater,"Register clicked")})
    }

}