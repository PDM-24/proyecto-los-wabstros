package com.example.codelesson.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _titleTopBar = MutableStateFlow("Desarrollador C++")
    val titleTopBar = _titleTopBar.asStateFlow()

    fun setTitle(title: String) {
        _titleTopBar.value = title
    }
}