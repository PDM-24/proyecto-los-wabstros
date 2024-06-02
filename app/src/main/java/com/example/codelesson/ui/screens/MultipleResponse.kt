package com.example.codelesson.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.components.practicecomponents.DetailedIndication
import com.example.codelesson.ui.components.practicecomponents.Hint
import com.example.codelesson.ui.components.practicecomponents.PracticeButton
import com.example.codelesson.ui.components.practicecomponents.ShortIndication
import com.example.codelesson.ui.theme.ButtonPurple1
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.DarkRed
import com.example.codelesson.ui.theme.NeonPurple
import com.example.codelesson.ui.theme.Red
import com.example.codelesson.ui.theme.audioWide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MultipleResponse (innerPadding: PaddingValues){
    
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BttnMultChoice(answer: String){
    val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope

    val isPressed = remember {
        mutableStateOf(false)
    }
    val animatedColor = animateColorAsState(
        targetValue = if (isPressed.value) NeonPurple else ButtonPurple1,
        animationSpec = tween(200, 0, LinearEasing)
    )

    val isIncorrect = remember {
        mutableStateOf(false)
    }

    OutlinedButton(
        modifier = Modifier
            .width(180.dp),
        onClick = {
            lifeCycleScope.launch(Dispatchers.IO){
                isPressed.value = true
                delay(310)
                isPressed.value = false
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if(!isIncorrect.value){
                animatedColor.value
            }else{
                Red
            }
        ),
        shape = RoundedCornerShape(15.dp),
        border = if(!isIncorrect.value){
            BorderStroke(2.dp, animatedColor.value)
        }else{
            BorderStroke(2.dp, DarkRed)
        }
    ) {
        Text(
            text = answer.uppercase(),
            fontFamily = audioWide,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}

private fun AnswOption(): List<String>{
    return listOf(
        "LEE LA VARIABLE NUMBERS",
        "GUARDA NUMBERS EN COUT",
        "IMPRIME 01234"
    )
}