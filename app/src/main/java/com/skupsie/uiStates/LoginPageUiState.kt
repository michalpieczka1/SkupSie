package com.skupsie.uiStates

data class LoginPageUiState(
    val isEmailValid:Boolean = false,
    val isPasswordValid:Boolean = false,
    val forgotPasswordOnClick: () -> Unit = {},
    val loginOnClick: () -> Unit = {},
    val registerOnClick: () -> Unit = {}
    //TODO add google api login support
)
