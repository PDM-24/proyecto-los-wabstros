package com.example.codelesson.data

data class QuizData(
    val question: String,
    val answer: String
)

fun quizList() : List<QuizData> {
    return listOf(
        QuizData(
            "Question 1",
            "Answer 1"
        ),
        QuizData(
            "Question 2",
            "Answer 2"
        ),
        QuizData(
            "Question 3",
            "Answer 3"
        ),
    )
}