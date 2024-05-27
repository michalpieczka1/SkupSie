package com.skupsie.screens.lessonScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
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
import com.skupsie.viewmodels.LessonViewModel

@Composable
fun LessonsPage(
    modifier: Modifier = Modifier,
    lessonViewModel: LessonViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController = rememberNavController(),
    currentUserId: Int
){
    val lessonUiState = lessonViewModel.uiState.collectAsState()

    LaunchedEffect(currentUserId) {
        lessonViewModel.updateCurrentUser(currentUserId)
        lessonViewModel.getLessons()
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        when {
            lessonUiState.value.isLoading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            lessonUiState.value.error != null -> ErrorScreen(lessonUiState = lessonUiState,modifier = Modifier.fillMaxSize())
            lessonUiState.value.lessons.isNotEmpty() -> MainLessonScreen(lessonViewModel = lessonViewModel, modifier = Modifier.fillMaxSize())
            else -> Text("No data available")
        }
    }
    //todo whole lesson page ui
}

@Composable
fun MainLessonScreen(
    lessonViewModel: LessonViewModel,
    modifier: Modifier = Modifier
) {
    val lessonUiState = lessonViewModel.uiState.collectAsState()
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
                            LessonCard(lesson = lesson, modifier = Modifier.weight(1f))
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
                    LessonCard(lesson = lesson, modifier = Modifier.fillMaxWidth())
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
    Card(modifier = if(!lesson.isPremium){
        modifier
            .size(cardSize)
    } else {
        modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .size(cardSize)
            .border(
                brush = Brush.verticalGradient(
                    0.25f to MaterialTheme.colorScheme.secondary,
                    1f to MaterialTheme.colorScheme.onTertiaryContainer
                ),
                width = 2.dp,
                shape = RoundedCornerShape(8.dp)
            )
    }

    )
    {
        Button(onClick = onClick) {
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

@Composable
fun measureTextHeight(text: String, style: TextStyle): Dp {
    val textMeasurer = rememberTextMeasurer()
    val widthInPixels = textMeasurer.measure(text =  text, style = style).size.height
    return with(LocalDensity.current) { widthInPixels.toDp() }
}