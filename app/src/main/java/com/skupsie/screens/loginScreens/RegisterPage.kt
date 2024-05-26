package com.skupsie.screens.loginScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skupsie.R
import com.skupsie.composables.AlternativeLoginOptions
import com.skupsie.composables.MainButton
import com.skupsie.composables.MainGradientButton
import com.skupsie.composables.MainTextField
import com.skupsie.composables.PasswordTextField
import com.skupsie.data.AppViewModelProvider
import com.skupsie.ui.theme.DarkPurple
import com.skupsie.ui.theme.SkupSieTheme
import com.skupsie.viewmodels.RegisterPageViewModel

@Composable
fun RegisterPage(
    modifier: Modifier = Modifier,
    registerPageViewModel: RegisterPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController = rememberNavController()
) {

    val registerPageUiState by registerPageViewModel.uiState.collectAsState()
    val context = LocalContext.current
    //to na dole to background image
    Box(
        modifier
            .fillMaxSize()
            .paint(
                painterResource(id = if (!isSystemInDarkTheme()) R.drawable.background_light else R.drawable.background_dark),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.height(160.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.named_logo),
                contentDescription = null,
                modifier = Modifier.width(256.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Rejestracja",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(horizontalAlignment = Alignment.End) {

                MainTextField(
                    gradientColor = DarkPurple,
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    value = registerPageViewModel.email,
                    onValueChange = { email -> registerPageViewModel.onEmailChange(email) },
                    isError = !registerPageUiState.isEmailValid
                )

                Spacer(modifier = Modifier.height(30.dp))

                PasswordTextField(
                    shadowColor = DarkPurple,
                    label = stringResource(R.string.password_text_label)+" "+stringResource(R.string.min_6_characters),
                    value = registerPageViewModel.password,
                    onValueChange = { password -> registerPageViewModel.onPasswordChange(password) },
                    isError = !registerPageUiState.isPasswordValid
                )

            }

            Spacer(modifier = Modifier.height(24.dp))

            MainGradientButton(
                btnText = stringResource(R.string.register),
                onClick = {
                    registerPageViewModel.registerOnClick(navController,context)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                btnText = stringResource(R.string.login),
                onClick = {registerPageViewModel.loginOnClick(navController)}
            )

            Spacer(modifier = Modifier.height(16.dp))

            AlternativeLoginOptions()

        }
    }
}



@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
    SkupSieTheme {
        RegisterPage()
    }
}