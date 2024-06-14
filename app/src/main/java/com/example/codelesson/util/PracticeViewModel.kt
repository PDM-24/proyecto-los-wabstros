package com.example.codelesson.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PracticeViewModel : ViewModel() {
    private val _titleTopBar = MutableStateFlow("Desarrollador C++")
    val titleTopBar = _titleTopBar.asStateFlow()

    fun setTitle(title: String) {
        _titleTopBar.value = title
    }
    fun VerifyingAnswer(answer: String, correctAnswer: String) =
        answer == correctAnswer
}