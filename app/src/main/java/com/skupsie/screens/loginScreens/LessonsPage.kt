package com.skupsie.screens.loginScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skupsie.data.AppViewModelProvider
import com.skupsie.data.User
import com.skupsie.viewmodels.LessonViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Composable
fun LessonsPage(
    modifier: Modifier = Modifier,
    lessonViewModel: LessonViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController = rememberNavController(),
    currentUserId: Int
){
    val lessonUiState = lessonViewModel.uiState.collectAsState()
    lessonViewModel.updateCurrentUser(currentUserId)

    val user by lessonUiState.value.user.collectAsState(initial = null)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = lessonUiState.value.lessons.size.toString())
    Button(onClick = { lessonViewModel.getLessons() }) {
    }

    }
    //todo whole lesson page ui
}