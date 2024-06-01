package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.Green
import com.example.codelesson.ui.theme.MoreTransparentWhite
import com.example.codelesson.ui.theme.audioWide

@Composable
fun PracticeButton(name: String, enable: Boolean, onClick: () -> Unit){
    OutlinedButton(
        modifier = Modifier
            .width(200.dp),
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color.Transparent,
            containerColor = Green
        ),
        border = if (enable){
            BorderStroke(2.dp, Color.White)
        }else{
            BorderStroke(2.dp, MoreTransparentWhite)
        },
        enabled = enable,
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = name.uppercase(),
            color = if (enable){
                Color.White
            }else{
                MoreTransparentWhite
            },
            fontFamily = audioWide,
            fontSize = 12.sp
        )
    }
}