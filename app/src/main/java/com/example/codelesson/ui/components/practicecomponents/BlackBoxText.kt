package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.poppins

@Composable
fun BlackBoxText(text: String){
    Text(
        text = text,
        color = Color.White,
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    )
}