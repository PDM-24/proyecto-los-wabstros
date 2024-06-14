package com.example.codelesson.util

import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusManager

object KeyboardFunctions{
    fun ClearFocus(focusManager: FocusManager, focused: MutableState<Boolean>){
        focusManager.clearFocus()
        focused.value = false
    }
}