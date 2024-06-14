package com.example.codelesson.ui.components.movinglabelcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelesson.ui.theme.ButtonPurple1
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.PracticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerLabel(text: String, practiceViewModel: PracticeViewModel) {
    val position by practiceViewModel.emptyLabelPosition.collectAsState()
    val d = LocalDensity.current
    var offsetX by remember {
        mutableStateOf(0f)
    }
    var offsetY by remember {
        mutableStateOf(0f)
    }

    Card(
        modifier = Modifier
            .offset(
                x = (offsetX/d.density).dp,
                y = (offsetY/d.density).dp
            )
            .width(136.dp)
            .height(41.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
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
    AnswerLabel(text = "", PracticeViewModel())
}