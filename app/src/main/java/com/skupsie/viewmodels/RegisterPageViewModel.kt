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
import com.skupsie.data.User
import com.skupsie.data.UserRepository
import com.skupsie.screens.loginScreens.LoginScreens
import com.skupsie.uiStates.RegisterPageUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterPageViewModel (
    private val userRepository: UserRepository
) : ViewModel() {
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
            currentState.copy(isEmailValid = email.contains('@'))
        }
    }
    private fun isPasswordValid(password: String) {
        _uiState.update { currentState ->
            currentState.copy(isPasswordValid = password.length >= 6)
        }
    }

    fun registerOnClick(navController: NavController,context: Context) {
        isEmailValid(email)
        isPasswordValid(password)

        viewModelScope.launch {
            // Switch to IO dispatcher for database operations
            val isUserInDb = withContext(Dispatchers.IO) {
                userRepository.isUserInDatabaseByEmail(email).first()
            }

            if (uiState.value.isEmailValid && uiState.value.isPasswordValid && !isUserInDb) {
                // Insert user in database on IO dispatcher
                withContext(Dispatchers.IO) {
                    userRepository.insertUser(
                        User(email = email, password = password)
                    )
                }
                val currentUserId = withContext(Dispatchers.IO){
                    userRepository.getUserByEmailAndPassword(email,password).first()?.id
                }

                val user = withContext(Dispatchers.IO){
                    currentUserId?.let { userRepository.getUserById(it) }
                }

                if(user?.first()?.name == ""){
                    withContext(Dispatchers.Main) {
                        navController.navigate("${LoginScreens.WelcomeNewUser.name}/${currentUserId}") //TODO navigate to main page not login
                    }
                }
                else{
                    withContext(Dispatchers.Main) {
                        navController.navigate("${LoginScreens.Lessons.name}/${currentUserId}") //TODO navigate to main page not login
                    }

                }
            } else if (isUserInDb) {
                // Update UI state on the main thread
                _uiState.update { currentState ->
                    currentState.copy(isEmailValid = false)
                }
                Toast.makeText(context, context.getString(R.string.email_exists), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginOnClick(navController: NavController){
            navController.navigate(LoginScreens.Login.name)
    }

}