package com.skupsie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skupsie.ui.theme.DarkPurple
import com.skupsie.ui.theme.SkupSieTheme
import com.skupsie.uiStates.LoginPageUiState
import com.skupsie.viewmodels.LoginPageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT)
        )

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = if(!isSystemInDarkTheme()) Color.White else Color(0xFF121212)
            ){
                SkupSieTheme {
                    // A surface container using the 'background' color from the theme
                    LoginPage(loginPageViewModel = LoginPageViewModel())
                }
            }
        }
    }
}

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    loginPageViewModel: LoginPageViewModel = viewModel()
) {

    val loginPageUiState by loginPageViewModel.uiState.collectAsState()

    //to na dole to background image
    Box(
        Modifier
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

            MainTextField(gradientColor = DarkPurple,
                label = "Email",
                keyboardType = KeyboardType.Email,
                value =  loginPageUiState.emailValue,
                onValueChange = loginPageViewModel.onEmailChange(it)
            )

            Spacer(modifier = Modifier.height(30.dp))

                PasswordTextField(shadowColor = DarkPurple,
                    label = "Hasło"
                )
                TextButton(onClick = { /*TODO nav to reset password page*/ }) {
                    Text(text = "Zapomniałeś hasła?",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            MainGradientButton(btnText = "Zaloguj się", onClick = {/*TODO nav to loging in*/})

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(btnText = "Stwórz konto", onClick = {/*TODO nav to creating account*/})

            Spacer(modifier = Modifier.height(16.dp))

            AlternativeLoginOptions()

        }
    }
}

@Composable
fun AlternativeLoginOptions(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(0.40f)
        )
        Text(
            text = "Lub",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(0.20f)
        )
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(0.40f)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier.shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(100.dp)
            )
        ) {
            IconButton(
                onClick = { /*TODO google*/ },
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(100.dp),
                        color = Color.White
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_google),
                    contentDescription = "Google Sign Up",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(128.dp)
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        Box(
            modifier = Modifier.shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(100.dp)
            )
        ) {
            IconButton(
                onClick = { /*TODO google*/ },
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(100.dp),
                        color = Color.White
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_facebook),
                    contentDescription = "Facebook Sign Up",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    gradientColor: Color, label: String,
    modifier: Modifier = Modifier,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType? = null,
    value: String,
    onValueChange: () -> Unit
) {

    TextField(
        value = value,
        onValueChange = { onValueChange },
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .shadow(
                16.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = gradientColor,
                spotColor = gradientColor
            )
            .height(64.dp)
            .fillMaxWidth(),
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(imageVector = trailingIcon, contentDescription = "Show password")
            }
        },
        keyboardOptions = if (keyboardType != null) KeyboardOptions(keyboardType = keyboardType)
        else KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    shadowColor: Color,
    label: String,
    modifier: Modifier = Modifier
) {
    var value by remember {
        mutableStateOf("")
    }

    var isPasswordShown by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .shadow(
                16.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = shadowColor,
                spotColor = shadowColor
            )
            .height(64.dp)
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { isPasswordShown = !isPasswordShown }) {
                if (!isPasswordShown) {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility_off_24px),
                        contentDescription = "Show password"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility_24px),
                        contentDescription = "Hide password"
                    )
                }
            }
        },
        visualTransformation = if (isPasswordShown) VisualTransformation.None
        else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun MainGradientButton(
    btnText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    0.25f to MaterialTheme.colorScheme.secondary,
                    1f to MaterialTheme.colorScheme.onTertiaryContainer
                ),
                shape = RoundedCornerShape(100.dp)
            )
            .height(56.dp)
    ) {
        Text(
            text = btnText,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.75f),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun MainButton(
    btnText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(
            containerColor = if(!isSystemInDarkTheme()) Color.Black else MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.height(56.dp)
    ) {
        Text(
            text = btnText,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.75f),
            color = MaterialTheme.colorScheme.onSecondary
        )
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