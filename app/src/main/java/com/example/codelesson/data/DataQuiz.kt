package com.example.codelesson.data

data class QuizData(
    val question: String,
    val answer: String
)

fun quizList() : List<QuizData> {
    return listOf(
        QuizData(
            "Question 1",
            "Numero Decimal"
        ),
        QuizData(
            "Question 2",
            "Numero Entero"
        ),
        QuizData(
            "Question 3",
            "Caracter"
        ),
    )
}