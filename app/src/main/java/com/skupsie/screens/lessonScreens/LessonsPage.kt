package com.skupsie.screens.lessonScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skupsie.R
import com.skupsie.data.AppViewModelProvider
import com.skupsie.data.Lesson
import com.skupsie.uiStates.LessonUiState
import com.skupsie.viewmodels.LessonPageViewModel

@Composable
fun LessonsPage(
    modifier: Modifier = Modifier,
    lessonPageViewModel: LessonPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController = rememberNavController(),
    currentUserId: Int
){
    val lessonUiState = lessonPageViewModel.uiState.collectAsState()

    LaunchedEffect(currentUserId) {
        lessonPageViewModel.updateCurrentUser(currentUserId)
        lessonPageViewModel.getLessons()
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        when {
            lessonUiState.value.isLoading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            lessonUiState.value.error != null -> ErrorScreen(lessonUiState = lessonUiState,modifier = Modifier.fillMaxSize())
            lessonUiState.value.lessons.isNotEmpty() -> MainLessonScreen(lessonPageViewModel = lessonPageViewModel, modifier = Modifier.fillMaxSize())
            else -> Text("No data available")
        }
    }
    //todo whole lesson page ui
}

@Composable
fun MainLessonScreen(
    lessonPageViewModel: LessonPageViewModel,
    modifier: Modifier = Modifier
) {
    val lessonUiState = lessonPageViewModel.uiState.collectAsState()
    val user by lessonUiState.value.user.collectAsState(initial = null)

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
            modifier = modifier.padding(horizontal = 16.dp, vertical = 64.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.named_logo),
                    contentDescription = null,
                    modifier = Modifier.weight(1.0f)
                )

                Spacer(Modifier.weight(1.0f))

                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu button", tint = MaterialTheme.colorScheme.onBackground)

                }
            }
            Text(
                text = "👋 Witaj ${user?.email ?: "użytkowniku"}",
                style = if(user?.isPremium == false){
                    MaterialTheme.typography.titleLarge
                }else{
                    MaterialTheme.typography.titleLarge.copy(
                        brush = Brush.horizontalGradient(
                            0.25f to MaterialTheme.colorScheme.onTertiaryContainer,
                            1f to MaterialTheme.colorScheme.secondary)
                    )
                },
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            if(user?.latestLessons?.isNotEmpty() == true){
            Text(
                text = "Twoje ostatnie lekcje",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    user?.let {
                        items(it.latestLessons){ lesson ->
                            LessonCard(
                                lesson = lesson,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    lessonPageViewModel.updateShowLesson(isLessonShowed = true)
                                    lessonPageViewModel.updateCurrentLesson(lesson = lesson)
                                }
                            )
                            if(lessonUiState.value.isLessonShowed){
                                LessonDialog(
                                    onDismiss = { lessonPageViewModel.updateShowLesson(false) },
                                    lesson = lessonUiState.value.currentLesson
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Proponowane lekcje",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(lessonUiState.value.lessons){ lesson ->
                    LessonCard(
                        lesson = lesson,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            lessonPageViewModel.updateShowLesson(isLessonShowed = true)
                            lessonPageViewModel.updateCurrentLesson(lesson = lesson)
                        }
                    )
                    if(lessonUiState.value.isLessonShowed){
                        LessonDialog(
                            onDismiss = { lessonPageViewModel.updateShowLesson(false) },
                            lesson = lessonUiState.value.currentLesson
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun LessonCard(
    lesson: Lesson,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    val cardSize:Dp = 192.dp
    Card(
        modifier = if(!lesson.isPremium){
            modifier
                .size(cardSize)
                .clickable(onClick = onClick)
    } else {
            modifier
                .size(cardSize)
                .border(
                    brush = Brush.verticalGradient(
                        0.25f to MaterialTheme.colorScheme.secondary,
                        1f to MaterialTheme.colorScheme.onTertiaryContainer
                    ),
                    width = 2.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable(onClick = onClick)
    }

    )
    {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(lesson.imageUrl)
                        .build(),
                    contentDescription = lesson.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(cardSize - 60.dp)
                )
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    lessonUiState: State<LessonUiState>
){
    Box(modifier = modifier, contentAlignment = Alignment.Center){
        Text(
            text = "Error ${lessonUiState.value.error} has appeared. Contact the owner of the application about this behavior",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
