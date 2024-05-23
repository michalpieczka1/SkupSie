package com.skupsie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skupsie.R

@Composable
fun AlternativeLoginOptions(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(0.40f),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
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
        HorizontalDivider(
            modifier = Modifier.weight(0.40f),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
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