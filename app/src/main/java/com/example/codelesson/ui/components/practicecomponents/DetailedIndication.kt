package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.poppins

@Composable
fun DetailedIndication (indication: String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ){
        Text(
            text = indication.uppercase(),
            color = Color.White,
            fontFamily = poppins,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp
        )
    }
}