package com.skupsie.screens.loginScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skupsie.R
import com.skupsie.composables.MainGradientButton
import com.skupsie.composables.MainTextField
import com.skupsie.data.AppViewModelProvider
import com.skupsie.data.User
import com.skupsie.viewmodels.WelcomeNewUserViewModel

@Composable
fun WelcomeNewUserScreen(
    modifier: Modifier = Modifier,
    welcomeNewUserViewModel: WelcomeNewUserViewModel = viewModel(factory = AppViewModelProvider.Factory),
    currentUserId:Int,
    navController: NavController = rememberNavController()
){
    val welcomeNewUserUiState = welcomeNewUserViewModel.uiState.collectAsState()
    LaunchedEffect(currentUserId) {
        welcomeNewUserViewModel.updateCurrentUser(currentUserId)
    }

    Box(
        modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.waving_people),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                Text(
                    text = "Hej 👋 \n Chyba jesteś tutaj pierwszy raz! Podaj nam jak chcesz się nazywać.",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            MainTextField(
                label = "Nazwa użytkownika",
                value = welcomeNewUserViewModel.name,
                onValueChange = { name -> welcomeNewUserViewModel.onNameChange(name) },
                isError = !welcomeNewUserUiState.value.isNameValid,
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            MainGradientButton(
                btnText = "Nazwij się!",
                onClick = { welcomeNewUserViewModel.onUserNameCreatorClick(navController) }
            )
        }

    }
}