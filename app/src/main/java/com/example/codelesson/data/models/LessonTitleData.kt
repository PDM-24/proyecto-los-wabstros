package com.example.codelesson.data.models

data class DataLesson(
    val message: String,
    val data: TitleLesson
)

data class TitleLesson(
    val id: String,
    val title: String
)