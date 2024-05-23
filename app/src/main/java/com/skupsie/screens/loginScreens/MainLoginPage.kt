package com.skupsie.screens.loginScreens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skupsie.viewmodels.ForgotPasswordViewModel
import com.skupsie.viewmodels.LoginPageViewModel
import com.skupsie.viewmodels.RegisterPageViewModel

enum class LoginScreens{
    Login,
    Register,
    ForgotPassword
}

@Composable
fun LoginApp(
    navController:  NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = LoginScreens.Login.name,
        enterTransition = { fadeIn(tween(300, easing = LinearEasing))},
        popExitTransition = { fadeOut() + slideOutHorizontally(tween(1000))}
    ){
        composable(route = LoginScreens.Login.name){
            LoginPage(
                loginPageViewModel = LoginPageViewModel(),
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
        composable(route = LoginScreens.Register.name){
            RegisterPage(
                registerPageViewModel = RegisterPageViewModel(),
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
        composable(route = LoginScreens.ForgotPassword.name){
            ForgotPasswordPage(
                forgotPasswordPageViewModel = ForgotPasswordViewModel(),
                modifier =  Modifier.fillMaxSize(),
                navController = navController
            )
        }
    }
}