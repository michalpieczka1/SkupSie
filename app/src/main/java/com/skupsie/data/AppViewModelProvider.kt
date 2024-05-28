package com.skupsie.data

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.skupsie.viewmodels.ForgotPasswordViewModel
import com.skupsie.viewmodels.LessonPageViewModel
import com.skupsie.viewmodels.LoginPageViewModel
import com.skupsie.viewmodels.RegisterPageViewModel
import com.skupsie.viewmodels.WelcomeNewUserViewModel
import kotlinx.coroutines.flow.emptyFlow

object AppViewModelProvider {
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

        initializer {
            LessonPageViewModel(inventoryApplication().container.lessonsRepository,inventoryApplication().container.userRepository, currentUser = emptyFlow())
        }

        initializer {
            WelcomeNewUserViewModel(inventoryApplication().container.userRepository)
        }
    }
}


fun CreationExtras.inventoryApplication(): SkupSieApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SkupSieApp)