package com.skupsie.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.skupsie.uiStates.RegisterPageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterPageViewModel() : ViewModel() {
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

    fun registerOnClick(){
        isEmailValid(email)
        isPasswordValid(password)

        if(uiState.value.isEmailValid && uiState.value.isPasswordValid){
            //TODO NAV
        }
    }

    fun loginOnClick(){
            //TODO NAV
    }
}