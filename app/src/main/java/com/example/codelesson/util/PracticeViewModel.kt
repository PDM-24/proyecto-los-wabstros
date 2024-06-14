package com.example.codelesson.util

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

    class PracticeViewModel : ViewModel() {
        private val _titleTopBar = MutableStateFlow("Desarrollador C++")
        val titleTopBar = _titleTopBar.asStateFlow()
        private val _emptyLabelPosition = MutableStateFlow(Offset.Zero)
        val emptyLabelPosition = _emptyLabelPosition.asStateFlow()

        fun getEmptyLabelPosition(position: Offset) {
            _emptyLabelPosition.value = position
        }
        fun setTitle(title: String) {
            _titleTopBar.value = title
        }
    }