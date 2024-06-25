package com.example.codelesson.model

data class Practice(
    val theme: String,
    val lesson: String,
    val recap: String,
    val questions: List<Question>
)
