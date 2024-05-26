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
import androidx.compose.material3.TextButton
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
import com.skupsie.data.LoginAppViewModelProvider
import com.skupsie.ui.theme.DarkPurple
import com.skupsie.ui.theme.SkupSieTheme
import com.skupsie.viewmodels.LoginPageViewModel


@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    loginPageViewModel: LoginPageViewModel = viewModel(factory = LoginAppViewModelProvider.Factory),
    navController: NavController = rememberNavController()
) {

    val loginPageUiState by loginPageViewModel.uiState.collectAsState()
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

            Text(text = "Logowanie",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(horizontalAlignment = Alignment.End) {

                MainTextField(
                    gradientColor = DarkPurple,
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    value = loginPageViewModel.email,
                    onValueChange = { email -> loginPageViewModel.onEmailChange(email) },
                    isError = !loginPageUiState.isEmailValid
                )

                Spacer(modifier = Modifier.height(30.dp))

                PasswordTextField(
                    shadowColor = DarkPurple,
                    label = stringResource(R.string.password_text_label) ,
                    value = loginPageViewModel.password,
                    onValueChange = { password -> loginPageViewModel.onPasswordChange(password) },
                    isError = !loginPageUiState.isPasswordValid
                )

                TextButton(onClick = {
                    loginPageViewModel.forgotPasswordOnClick(navController)
                } ) {
                    Text(text = stringResource(R.string.forgot_password),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            MainGradientButton(
                btnText = stringResource(R.string.login),
                onClick = { loginPageViewModel.loginOnClick(navController,context) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                btnText = stringResource(R.string.register),
                onClick = { loginPageViewModel.registerOnClick(navController) }
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
fun LoginPagePreview() {
    SkupSieTheme {
        LoginPage()
    }
}