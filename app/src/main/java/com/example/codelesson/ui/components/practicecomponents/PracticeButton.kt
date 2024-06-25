package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
    val animatedColorContainer = animateColorAsState(
        targetValue = if(enable) Green else Color.Transparent,
        animationSpec = tween(200, 0, LinearEasing)
    )

    val animatedWhite = animateColorAsState(
        targetValue = if(enable) Color.White else MoreTransparentWhite,
        animationSpec = tween(200, 0, LinearEasing)
    )

    OutlinedButton(
        modifier = Modifier
            .width(200.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = animatedColorContainer.value,
            containerColor = animatedColorContainer.value
        ),
        border = BorderStroke(2.dp, animatedWhite.value),
        enabled = enable,
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = name.uppercase(),
            color = animatedWhite.value,
            fontFamily = audioWide,
            fontSize = 12.sp
        )
    }
}