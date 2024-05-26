package com.skupsie.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.skupsie.R
import com.skupsie.data.UserRepository
import com.skupsie.screens.loginScreens.LoginScreens
import com.skupsie.uiStates.LoginPageUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPageViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginPageUiState())
    val uiState: StateFlow<LoginPageUiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun onEmailChange(input: String) {
        email = input
    }

    fun onPasswordChange(input: String) {
        password = input
    }

    private fun isEmailValid(email: String) {
        val isValid: Boolean = (email.contains('@') && email.isNotEmpty())
        _uiState.update { currentState ->
            currentState.copy(isEmailValid = isValid)
        }
    }

    private fun isPasswordValid(password: String) {
        val isValid: Boolean = (password.length >= 6 && password.isNotEmpty())
        _uiState.update { currentState ->
            currentState.copy(isPasswordValid = isValid)
        }
    }

    fun forgotPasswordOnClick(navController: NavController) {
        navController.navigate(LoginScreens.ForgotPassword.name)
    }

    fun loginOnClick(navController: NavController,context: Context) {
        isEmailValid(email)
        isPasswordValid(password)

        viewModelScope.launch {
            val isUserInDb = withContext(Dispatchers.IO){
                userRepository.isUserInDatabaseByEmailAndPassword(email, password)
                    .first()
            }
            if (isUserInDb) {

                val currentUserId = withContext(Dispatchers.IO){
                    userRepository.getUserByEmailAndPassword(email,password).first()?.id
                }

                withContext(Dispatchers.Main) {
                    navController.navigate("${LoginScreens.Lessons.name}/${currentUserId}") //TODO navigate to main page not login
                }

            } else {
                _uiState.update { currentState ->
                    currentState.copy(isEmailValid = false)
                }
                Toast.makeText(context,
                    context.getString(R.string.account_doesnt_exist), Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun registerOnClick(navController: NavController) {
        navController.navigate(LoginScreens.Register.name)
    }

}
