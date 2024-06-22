package com.example.codelesson.data

data class QuizData(
    val question: String,
    val answer: String
)

fun quizList() : List<QuizData> {
    return listOf(
        QuizData(
            "Question 1",
            "Numero\nDecimal"
        ),
        QuizData(
            "Question 2",
            "Numero\nEntero"
        ),
        QuizData(
            "Question 3",
            "Caracter"
        ),
    )
}