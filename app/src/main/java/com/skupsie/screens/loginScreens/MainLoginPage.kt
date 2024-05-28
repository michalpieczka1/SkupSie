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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skupsie.screens.lessonScreens.LessonsPage

enum class LoginScreens{
    Login,
    Register,
    ForgotPassword,
    Lessons,
    WelcomeNewUser
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
        composable(
            LoginScreens.WelcomeNewUser.name+"/{currentUserId}",
            arguments = listOf(
                navArgument(name = "currentUserId"){
                    type = NavType.IntType
                }
            )
            ){backStackEntry ->
                WelcomeNewUserScreen(
                    currentUserId = backStackEntry.arguments?.getInt("currentUserId")!!,
                    navController = navController
                )
            }
    }
}