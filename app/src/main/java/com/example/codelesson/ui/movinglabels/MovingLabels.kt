package com.example.codelesson.ui.movinglabels

import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.codelesson.data.QuizData
import com.example.codelesson.ui.components.movinglabelcomponents.AnswerLabel
import com.example.codelesson.ui.components.movinglabelcomponents.DragTarget
import com.example.codelesson.ui.components.movinglabelcomponents.DropItem
import com.example.codelesson.ui.components.movinglabelcomponents.LabelEmpty
import com.example.codelesson.ui.components.movinglabelcomponents.QuestionLabel
import com.example.codelesson.ui.components.navigation.Graph
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.navigation.QuizGraph
import com.example.codelesson.ui.components.practicecomponents.Hint
import com.example.codelesson.ui.components.practicecomponents.PracticeButton
import com.example.codelesson.ui.components.practicecomponents.ShortIndication
import com.example.codelesson.ui.theme.ButtonPurple1
import com.example.codelesson.ui.theme.DarkRed
import com.example.codelesson.ui.theme.Red
import com.example.codelesson.util.AnimatingColors
import com.example.codelesson.util.PracticeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovingLabels (
    innerPadding: PaddingValues,
    viewModel: PracticeViewModel,
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val practiceList by viewModel.practiceList.collectAsState()
        val nextRoute by viewModel.nextNavigationRoute.collectAsState()
        val index by viewModel.index.collectAsState()
        val endIndicator = remember {
            viewModel.endIndicator.value
        }
        val questionsList by viewModel.questionList.collectAsState()
        val correctAnswer = questionsList[endIndicator-1].correctAnswer

        var isSelected by remember {
            mutableStateOf(false)
        }
        var isCorrect by remember {
            mutableStateOf(true)
        }

        Hint(
            hint = questionsList[endIndicator-1].hint,
            isIncorrect = !isCorrect
        )
        ShortIndication(
            indication = "Encuentre la definicion"
        )
        //Obtaining the width of the dispositive
        val screenWidth = LocalConfiguration.current.screenWidthDp
        var text by remember {
            mutableStateOf("")
        }
        val list = mutableListOf(
            questionsList[endIndicator-1].correctAnswer
        )
        questionsList[endIndicator-1].incorrectAnswers.forEach {
            list.add(it)
        }
        val dataQuiz: MutableList<QuizData> = mutableListOf()

        list.forEachIndexed { index, s ->
            dataQuiz.add(QuizData("question ${index}", s))
        }

        val animatedColorSelectionContainer =
            if (isCorrect)
                ButtonPurple1
            else
                Red

        val animatedColorSelectionBorder =
            if (isCorrect)
                ButtonPurple1
            else
                DarkRed

        val colorToDefault = ButtonPurple1
        val borderToDefault = ButtonPurple1

        val animatedColorContainer = AnimatingColors.animatingColor(color = animatedColorSelectionContainer)
        val animatedColorBorder = AnimatingColors.animatingColor(color = animatedColorSelectionBorder)

        val colorDefault = AnimatingColors.animatingColor(color = colorToDefault)
        val borderDefault = AnimatingColors.animatingColor(color = borderToDefault)

        val backHandlerActive = remember {
            mutableStateOf(true)
        }

        val context = LocalContext.current

        LaunchedEffect(true) {
            viewModel.resetNavRoute()
        }

        if(nextRoute == "")
            viewModel.verifyTypeOfQuestion(index)


        BackHandler{
            if(backHandlerActive.value){
                backHandlerActive.value = false
                Toast.makeText(context, "Presiona de nuevo para regresar al menú principal", Toast.LENGTH_SHORT).show()
            }else{
                navController.navigate(HomeGraph.Home.route){
                    popUpTo(Graph.HOME.graph) {
                        inclusive = true
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuestionLabel(questionsList[endIndicator-1].question)

            DropItem<QuizData>(
                modifier = Modifier
                    .width(140.dp)
                    .size(Dp(screenWidth / 3.5f))
            ) { isInBound, quizItem ->
                if (quizItem != null) {
                    LaunchedEffect(dataQuiz) {
                        text = quizItem.answer
                        isSelected = true
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
                        AnswerLabel(text, animatedColorContainer, animatedColorBorder)
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
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                columns = GridCells.Fixed(2),
            ) {
                items(dataQuiz) {
                    if ((it.answer != text) && (it != dataQuiz[2])) {
                        //Elemento que podra ser arrastrado
                        DragTarget(
                            dataDrop = it,
                            viewModel = viewModel
                        ) {
                            //Contenido del elemento arrastrable
                            AnswerLabel(text = it.answer, colorDefault, borderDefault)
                        }
                    }
                }
            }
            //Se renderiza fuera del verticalgrid para poder centrar el tercer elememto
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                if (dataQuiz[2].answer != text) {
                    DragTarget(
                        dataDrop = dataQuiz[2],
                        viewModel = viewModel
                    ) {
                        AnswerLabel(text = dataQuiz[2].answer, colorDefault, borderDefault)
                    }
                }
            }
            val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope
            Spacer(modifier = Modifier.padding(24.dp))
            PracticeButton(name = "Seguir", enable = isSelected) {
                if (viewModel.VerifyingAnswer(text, correctAnswer)) {
                    if(endIndicator == questionsList.size){
                        navController.navigate(QuizGraph.LessonRecap.route)
                    }else{
                        if(nextRoute != ""){
                            navController.navigate(nextRoute)

                            viewModel.resetNavRoute()

                            viewModel.addIndex()
                        }
                    }
                }
                else {
                    lifeCycleScope.launch {
                        isCorrect = false
                        isSelected = false
                        delay(600)
                        text = ""
                        isCorrect = true
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MovingLabelPreview() {
    MovingLabels(innerPadding = PaddingValues(), PracticeViewModel(), NavHostController(LocalContext.current))
}