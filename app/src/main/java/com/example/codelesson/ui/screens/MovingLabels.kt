package com.example.codelesson.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codelesson.ui.components.movinglabelcomponents.LabelEmpty
import com.example.codelesson.ui.components.movinglabelcomponents.QuestionLabel
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.components.practicecomponents.ShortIndication
import com.example.codelesson.util.PracticeViewModel

@Composable
fun MovingLabels (innerPadding: PaddingValues, viewModel: PracticeViewModel){
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        CodeBlock(code = "INT ES UNA VARIABLE DE TIPO ENTERO CON UN RANGO DE -32768 A 32767.")
        ShortIndication(indication = "Encuentre la definicion")

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuestionLabel()
            LabelEmpty()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MovingLabelPreview() {
    MovingLabels(innerPadding = PaddingValues(), viewModel = PracticeViewModel())
}