package com.example.codelesson.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.navigation.Graph
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.navigation.QuizGraph
import com.example.codelesson.ui.components.practicecomponents.BlackBoxText
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
import com.example.codelesson.util.AnimatingColors
import com.example.codelesson.util.PracticeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val isIncorrect: MutableState<Boolean> = mutableStateOf(false)
private val actualAnswer: MutableState<String> = mutableStateOf("")
private val changeAnswer: MutableState<Boolean> = mutableStateOf(false)
private val thereIsAnAnswer: MutableState<Boolean> = mutableStateOf(false)

@Composable
fun MultipleResponse (
    innerPadding: PaddingValues,
    viewModel: PracticeViewModel,
    navController: NavHostController
){
    val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope
    val correctAnswer = "IMPRIME 01234"

    val nextRoute by viewModel.nextNavigationRoute.collectAsState()
    val index by viewModel.index.collectAsState()
    val endIndicator by viewModel.endIndicator.collectAsState()
    val questionsList by viewModel.questionList.collectAsState()

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
                popUpTo(Graph.HOME.graph)
            }
        }
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(DarkGrey)
        .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Hint(
                hint = "El cout permite mostrar por pantalla cualquier tipo de dato.",
                isIncorrect = isIncorrect.value
            )
            ShortIndication(indication = "escoge la opción correcta")
            DetailedIndication(indication = "que hace el codigo?")

            CodeBlock {
                BlackBoxText(text = "int NUMBERS = 01234;\n" +
                        "\n" +
                        "cout << NUMBERS;")

            }

            Spacer(modifier = Modifier.padding(2.dp))

            AnswOption().forEach {
                BttnMultChoice(answer = it, correctAnswer = correctAnswer)
                Spacer(modifier = Modifier.padding(5.dp))
            }

            Spacer(modifier = Modifier.padding(3.dp))

            PracticeButton(name = "Seguir", enable = thereIsAnAnswer.value) {
                if(VerifyingAnswer(actualAnswer.value, correctAnswer)){
                    isIncorrect.value = false

                    if(endIndicator == questionsList.size){
                        navController.navigate(QuizGraph.LessonRecap.route)
                    }else{
                        if(nextRoute != ""){
                            navController.navigate(nextRoute)

                            viewModel.resetNavRoute()

                            viewModel.addIndex()
                        }
                    }
                }else{
                    isIncorrect.value = true

                    thereIsAnAnswer.value = false

                    lifeCycleScope.launch(Dispatchers.IO){
                        delay(310)
                        actualAnswer.value = ""
                        isIncorrect.value = false
                    }
                }
            }
        }
    }
}


@Composable
private fun BttnMultChoice(answer: String, correctAnswer: String){
    val isPressed = remember {
        mutableStateOf(false)
    }

    val animatedColorSelectionContainer =
        if (isPressed.value&&!isIncorrect.value)
            NeonPurple
        else if(isPressed.value && isIncorrect.value)
            Red
        else
            ButtonPurple1

    val animatedColorSelectionBorder =
        if (isPressed.value&&!isIncorrect.value)
            NeonPurple
        else if(isPressed.value && isIncorrect.value)
            DarkRed
        else
            ButtonPurple1


    val animatedColorContainer = AnimatingColors.animatingColor(color = animatedColorSelectionContainer)

    val animatedColorBorder = AnimatingColors.animatingColor(color = animatedColorSelectionBorder)

    if(isPressed.value && answer != actualAnswer.value){
        isPressed.value = false
        changeAnswer.value = false
    }

    OutlinedButton(
        modifier = Modifier
            .width(180.dp),
        onClick = {
            if(isPressed.value && answer == actualAnswer.value)
                isPressed.value = false
            else if(!isPressed.value && answer != actualAnswer.value){
                isPressed.value = true
                changeAnswer.value = true
            }

            if(isPressed.value){
                actualAnswer.value = answer
                thereIsAnAnswer.value = true
            }else{
                actualAnswer.value = ""
                thereIsAnAnswer.value = false
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColorContainer.value
        ),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, animatedColorBorder.value)
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

fun VerifyingAnswer(answer: String, correctAnswer: String) =
    answer == correctAnswer