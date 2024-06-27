package com.example.codelesson.ui.components.movinglabelcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.audioWide

@Composable
fun AnswerLabel(text: String, color: State<Color>, animatedColorContainer: State<Color>) {
    Card(
        modifier = Modifier
            .width(136.dp)
            .height(45.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(4.dp, animatedColorContainer.value, RoundedCornerShape(12.dp))
                .background(color.value)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontFamily = audioWide,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}