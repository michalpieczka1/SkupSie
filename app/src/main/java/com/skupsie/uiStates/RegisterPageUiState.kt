package com.skupsie.uiStates

data class RegisterPageUiState(
    val isEmailValid:Boolean = true,
    val isPasswordValid:Boolean = true
    //TODO add google api login support
)
