package com.example.codelesson.model

data class Question(
    val type: Int,
    val code: String,
    val question: String,
    val hint: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
