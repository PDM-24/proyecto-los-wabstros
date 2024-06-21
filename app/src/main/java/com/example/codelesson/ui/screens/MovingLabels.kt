package com.example.codelesson.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.codelesson.data.QuizData
import com.example.codelesson.data.quizList
import com.example.codelesson.ui.components.movinglabelcomponents.AnswerLabel
import com.example.codelesson.ui.components.movinglabelcomponents.DragTarget
import com.example.codelesson.ui.components.movinglabelcomponents.DropItem
import com.example.codelesson.ui.components.movinglabelcomponents.LabelEmpty
import com.example.codelesson.ui.components.movinglabelcomponents.QuestionLabel
import com.example.codelesson.ui.components.practicecomponents.BlackBoxText
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.components.practicecomponents.ShortIndication
import com.example.codelesson.util.PracticeViewModel

@Composable
fun MovingLabels (
    innerPadding: PaddingValues,
    practiceViewModel: PracticeViewModel
){
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        CodeBlock {
            BlackBoxText(text = "INT ES UNA VARIABLE DE TIPO ENTERO CON UN RANGO DE -32768 A 32767.")
        }
        ShortIndication(
            indication = "Encuentre la definicion"
        )
        //Obtiene el ancho del dispositivo
        val screenWidth = LocalConfiguration.current.screenWidthDp
        var text by remember {
            mutableStateOf("")
        }
        val list = quizList()

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuestionLabel("INT")

            DropItem<QuizData>(
                modifier = Modifier
                    .width(140.dp)
                    .size(Dp(screenWidth / 3.5f))
            ) { isInBound, quizItem ->
                if (quizItem != null) {
                    LaunchedEffect(quizItem) {
                        text = quizItem.answer
                    }
                }
                //Verifica si el elemento arrastrado esta dentro del area de drop item
                if (isInBound) {
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .border(1.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
                            .background(Color.Gray.copy(0.5f), RoundedCornerShape(15.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        LabelEmpty()
                    }
                } else {
                    if(text != ""){
                        AnswerLabel(text)
                    }
                    else{
                        Box(
                            modifier = Modifier
                                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
                                .background(Color.Black.copy(0.6f), RoundedCornerShape(15.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            LabelEmpty()
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(24.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(list) {
                    if ((it.answer != text) && (it != list[2])) {
                        //Elemento que podra ser arrastrado
                        DragTarget(
                            dataDrop = it,
                            viewModel = practiceViewModel
                        ) {
                            //Contenido del elemento arrastrable
                            AnswerLabel(text = it.answer)
                        }
                    }
                }
            }
            //Se renderiza fuera del verticalgrid para poder centrar el tercer elememto
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (list[2].answer != text) {
                    DragTarget(
                        dataDrop = list[2],
                        viewModel = practiceViewModel
                    ) {
                        AnswerLabel(text = list[2].answer)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MovingLabelPreview() {
    MovingLabels(innerPadding = PaddingValues(), PracticeViewModel())
}