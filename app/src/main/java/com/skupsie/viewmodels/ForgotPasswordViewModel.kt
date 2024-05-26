package com.skupsie.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.skupsie.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForgotPasswordViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    fun onEmailChange(value:String){
        email = value
    }

     fun sendCodeOnClick(context: Context){
       viewModelScope.launch {
           val message = withContext(Dispatchers.IO){
               userRepository.getUserByEmail(email).first()?.password ?: "Taki email nie istnieje"
           }
               Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
       }
    }
    fun backOnClick(navController: NavController){
        navController.navigateUp()
    }
}