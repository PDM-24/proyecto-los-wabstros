package com.example.codelesson.data.models

data class LessonData(
    val id: String,
    val title: String,
    val lesson: String,
    val recap: String,
)

data class Questions(
    val id: String,
    val type: Int,
    val code: String,
    val hint: String,
    val question: String,
    val correctAnswer: String,
    val options: List<String>,
    val lessonTitle: String
)
