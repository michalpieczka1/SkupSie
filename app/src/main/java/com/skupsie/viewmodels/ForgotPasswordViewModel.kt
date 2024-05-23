package com.skupsie.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.skupsie.uiStates.ForgotPasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set

    fun onEmailChange(value:String){
        email = value
    }

    fun sendCodeOnClick(){
        //TODO check if email is in db and show toast with password until no email system is added
    }
    fun backOnClick(){
        //TODO NAV to login page
    }
}