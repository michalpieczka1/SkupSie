package com.skupsie.data

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.skupsie.viewmodels.ForgotPasswordViewModel
import com.skupsie.viewmodels.LoginPageViewModel
import com.skupsie.viewmodels.RegisterPageViewModel

object LoginAppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RegisterPageViewModel(inventoryApplication().container.userRepository)
        }

        initializer {
            ForgotPasswordViewModel(inventoryApplication().container.userRepository)
        }

        initializer {
            LoginPageViewModel(inventoryApplication().container.userRepository)
        }
    }
}


fun CreationExtras.inventoryApplication(): LoginApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LoginApplication)