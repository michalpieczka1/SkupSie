package com.skupsie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainGradientButton(
    btnText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
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
        onClick = onClick,
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