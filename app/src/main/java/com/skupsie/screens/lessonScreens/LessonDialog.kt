package com.skupsie.screens.lessonScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skupsie.data.Lesson
import com.skupsie.data.UserPremiumStatus

@Composable
fun LessonDialog(
    onDismiss: () -> Unit,
    lesson: Lesson,
    isUserPremiumStatus: Boolean
) {
    if (lesson.isPremium && !isUserPremiumStatus) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Text(
                    text = "Polepsz status konta na premium",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            text = {
                Text(text = "Niestety ale nie posiadasz konta premium żeby zobaczyć tą lekcję 😥")
            },
            modifier = Modifier.padding(8.dp)
        )
    } else {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = { /*TODO*/ },
            title = {
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = lesson.description)

                    for (i in lesson.steps.indices) {
                        Text(
                            text = "${i + 1}. ${lesson.steps[i]}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                }
            },
            modifier = Modifier.padding(8.dp)
        )
    }
}