package com.skupsie.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.skupsie.R
import com.skupsie.ui.theme.DarkPurple

@Composable
fun MainTextField(
    modifier: Modifier = Modifier,
    gradientColor: Color = DarkPurple,
    label: String,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType? = null,
    value:String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        isError = isError,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
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
        else KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}

@Composable
fun PasswordTextField(
    shadowColor: Color,
    label: String,
    modifier: Modifier = Modifier,
    value:String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {

    var isPasswordShown by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        isError = isError,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
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