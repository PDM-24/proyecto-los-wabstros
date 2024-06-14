package com.example.codelesson.util

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

object AnimatingColors {
    @Composable
    fun animatingColor(
        inicialColor: Color,
        reactiveColor: Color,
        condition: MutableState<Boolean>
    ): State<Color>{
        return animateColorAsState(
            targetValue = if(condition.value) reactiveColor else inicialColor,
            animationSpec = tween(200, 0, LinearEasing)
        )
    }

    @Composable
    fun animatingColor(
        color: Color
    ): State<Color>{
        return animateColorAsState(
            targetValue = color,
            animationSpec = tween(200, 0, LinearEasing)
        )
    }
}