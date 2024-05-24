package com.skupsie.uiStates

import com.skupsie.data.UserInfo

data class RegisterPageUiState(
    val isEmailValid:Boolean = true,
    val isPasswordValid:Boolean = true
    //TODO add google api login support
)
