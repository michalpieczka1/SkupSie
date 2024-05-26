package com.skupsie.screens.loginScreens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skupsie.data.User
import kotlinx.coroutines.flow.Flow

enum class LoginScreens{
    Login,
    Register,
    ForgotPassword,
    Lessons
}

@Composable
fun LoginApp(
    navController:  NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = LoginScreens.Login.name,
        enterTransition = { fadeIn(tween(300, easing = LinearEasing))},
        popExitTransition = { fadeOut() + slideOutHorizontally(tween(1000))}
    ){
        composable(route = LoginScreens.Login.name){
            LoginPage(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
        composable(route = LoginScreens.Register.name){
            RegisterPage(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
        composable(route = LoginScreens.ForgotPassword.name){
            ForgotPasswordPage(
                modifier =  Modifier.fillMaxSize(),
                navController = navController
            )
        }
        composable(
            route = LoginScreens.Lessons.name+"/{currentUserId}",
            arguments = listOf(
                navArgument(name = "currentUserId"){
                    type = NavType.IntType
                }
            )
        ){ backStackEntry ->

            LessonsPage(
                modifier = Modifier.fillMaxSize(),
                currentUserId = backStackEntry.arguments?.getInt("currentUserId")!!
            )
        }
    }
}