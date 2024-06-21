package com.example.codelesson.ui.components.movinglabelcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelesson.ui.theme.ButtonPurple1
import com.example.codelesson.ui.theme.poppins

@Composable
fun AnswerLabel(text: String) {
    Card(
        modifier = Modifier
            .width(136.dp)
            .height(41.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ButtonPurple1),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontFamily = poppins,
                fontWeight = FontWeight.W400,
            )
        }
    }
}

@Preview
@Composable
private fun AnswerPreview() {
    AnswerLabel(text = "")
}