package com.example.codelesson.model

import androidx.compose.runtime.MutableState

data class LessonStatus(
    val title: String,
    val complete: MutableState<Boolean>
)
