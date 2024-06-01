package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.poppins

@Composable
fun CodeBlock(code: String){
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
        Text(
            text = code,
            color = Color.White,
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
    }
}