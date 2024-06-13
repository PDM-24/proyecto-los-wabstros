package com.example.codelesson.ui.components.homecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.audioWide

@Composable
fun TopBar(
    modifier: Modifier,
    title: String
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontFamily = audioWide
        )
    }
}