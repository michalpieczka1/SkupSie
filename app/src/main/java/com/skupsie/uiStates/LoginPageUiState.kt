package com.skupsie.uiStates

data class LoginPageUiState(
    val isEmailValid:Boolean = true,
    val isPasswordValid:Boolean = true
    //TODO add google api login support
)
