package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CodeBlock(
    content: @Composable() () -> Unit
){
    Spacer(modifier = Modifier.padding(3.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(
                horizontal = 30.dp,
                vertical = 20.dp
            )
    ) {
        content()
    }
}