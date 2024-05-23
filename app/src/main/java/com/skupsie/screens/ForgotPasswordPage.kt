package com.skupsie.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skupsie.R
import com.skupsie.composables.MainButton
import com.skupsie.composables.MainGradientButton
import com.skupsie.composables.MainTextField
import com.skupsie.ui.theme.SkupSieTheme
import com.skupsie.viewmodels.ForgotPasswordViewModel

@Composable
fun ForgotPasswordPage(
    modifier: Modifier = Modifier,
    forgotPasswordPageViewModel: ForgotPasswordViewModel = viewModel()
){
    val forgotPasswordUiState by forgotPasswordPageViewModel.uiState.collectAsState()
    Box(
        modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.forgot_password_background),
                contentScale = ContentScale.Fit
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Box(){
                Text(
                    text = stringResource(R.string.forgot_password_title),
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.labelLarge.copy(
                        drawStyle = Stroke(
                            width = 5f,
                            miter = 10f,
                            join = StrokeJoin.Round
                        )
                    ),
                    minLines = 3,
                    textAlign = TextAlign.Center,

                    )
                Text(
                    text = stringResource(R.string.forgot_password_title),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelLarge,
                    minLines = 3,
                    textAlign = TextAlign.Center,

                )


            }

            Spacer(modifier = Modifier.height(24.dp))

            MainTextField(
                label = "Email",
                value = forgotPasswordUiState.email,
                onValueChange = {email -> forgotPasswordPageViewModel.onEmailChange(email)}
            )

            Spacer(modifier = Modifier.height(30.dp))

            MainGradientButton(
                btnText = stringResource(R.string.send_code),
                onClick = { forgotPasswordPageViewModel.sendCodeOnClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                btnText = stringResource(R.string.back),
                onClick = {forgotPasswordPageViewModel.backOnClick()}
            )
        }
    }
}


@PreviewLightDark
@PreviewFontScale
@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    SkupSieTheme {
        ForgotPasswordPage()
    }
}