package com.skupsie.uiStates

import com.skupsie.data.User
import kotlinx.coroutines.flow.Flow

data class WelcomeNewUserUiState(
    val currentUser: User? = null,
    val isNameValid:Boolean = true
    )
