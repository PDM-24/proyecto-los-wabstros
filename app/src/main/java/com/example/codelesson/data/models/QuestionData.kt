package com.example.codelesson.data.models

data class QuestionModel(
    val message: String,
    val data: List<QuestionData>
)

data class QuestionData(
    val type: Int,
    val code: String,
    val hint: String,
    val questions: String,
    val correctAnswer: String,
    val options: List<String>
)