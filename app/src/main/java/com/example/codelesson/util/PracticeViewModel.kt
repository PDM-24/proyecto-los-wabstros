package com.example.codelesson.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PracticeViewModel : ViewModel() {
    //Para establecer el titulo de la topbar del scaffold
    private val _titleTopBar = MutableStateFlow("Desarrollador C++")
    val titleTopBar = _titleTopBar.asStateFlow()
    //Para checar que una label esta siendo arrastrado
    private val _isCurrentlyDragging = MutableLiveData(false)
    private var isCurrentlyDragging = _isCurrentlyDragging

    fun startDragging() {
        _isCurrentlyDragging.value = true
    }

    fun stopDragging() {
        isCurrentlyDragging.value = false
    }

    fun setTitle(title: String) {
        _titleTopBar.value = title
    }

    fun VerifyingAnswer(answer: String, correctAnswer: String) =
        answer == correctAnswer
}