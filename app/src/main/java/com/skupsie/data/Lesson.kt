package com.skupsie.data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val description: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("premium")
    val isPremium: Boolean,
    val steps: List<String>,
    val title: String,
    @SerialName("video_url")
    val videoUrl: String
)