package com.example.codelesson.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.navigation.Graph
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.practicecomponents.BlackBoxText
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.components.practicecomponents.DetailedIndication
import com.example.codelesson.ui.components.practicecomponents.Hint
import com.example.codelesson.ui.components.practicecomponents.PracticeButton
import com.example.codelesson.ui.components.practicecomponents.ShortIndication
import com.example.codelesson.ui.components.rememberImeState
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.FocusedFormWhite
import com.example.codelesson.ui.theme.FormWhite
import com.example.codelesson.ui.theme.LetterTransparentBlack
import com.example.codelesson.ui.theme.Red
import com.example.codelesson.ui.theme.TransparentWhite
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.AnimatingColors
import com.example.codelesson.util.KeyboardFunctions
import com.example.codelesson.util.PracticeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val actualAnswer: MutableState<String> = mutableStateOf("")
private val isIncorrect: MutableState<Boolean> = mutableStateOf(false)

@Composable
fun ResponseEntry (
    innerPadding: PaddingValues,
    viewModel: PracticeViewModel,
    navController: NavHostController
){
    val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope

    val focused = remember {
        mutableStateOf(false)
    }

    val imeState = rememberImeState()
    val scrollState = rememberLazyListState()

    val focusManager = LocalFocusManager.current
    val correctAnswer = ">"

    val animatedColorContainer = AnimatingColors.animatingColor(
        inicialColor = FormWhite,
        reactiveColor = Red,
        condition = isIncorrect
    )

    val code = "if(n $$ 3){\n\n...\n\n}"

    val splitedCode = code.split("$$")

    val nextRoute by viewModel.nextNavigationRoute.collectAsState()
    val index by viewModel.index.collectAsState()

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

    LaunchedEffect(imeState.value) {
        if(imeState.value){
            focused.value = true
            delay(150)
            scrollState.animateScrollToItem(1)
        }
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrey)
            .padding(innerPadding)
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                KeyboardFunctions.ClearFocus(focusManager, focused)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState
    ) {
        item(0) {
            Hint(hint = "El operador de lógico > permite saber cuando un número es mayor o igual a otro.",
                isIncorrect = isIncorrect.value)

            ShortIndication(indication = "Completa con la respuesta correcta")

            DetailedIndication(indication = "validacion para entrar al if si n es mayor que 3")

            CodeBlock {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BlackBoxText(text = splitedCode[0])

                        Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                        if(actualAnswer.value == ""){
                            Box(modifier = Modifier
                                .height(15.dp)
                                .width(15.dp)
                                .background(Color.White)
                            )
                        }else{
                            BlackBoxText(text = actualAnswer.value)
                        }
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                    BlackBoxText(text = splitedCode[1])
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            TextField(
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp)
                    .padding(0.dp),
                value = actualAnswer.value,
                onValueChange = {
                    actualAnswer.value = it
                },
                maxLines = 1,
                minLines = 1,
                singleLine = true,
                placeholder = { Text(
                    text = "Ingrese el error",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = animatedColorContainer.value,
                    focusedContainerColor = FocusedFormWhite,
                    unfocusedPlaceholderColor = TransparentWhite,
                    focusedPlaceholderColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = TransparentWhite,
                    cursorColor = LetterTransparentBlack,
                ),
                shape = RoundedCornerShape(13.dp),
                textStyle = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        if(actualAnswer.value != "")
                            responseHandler(
                                lifeCycleScope,
                                correctAnswer,
                                focusManager,
                                focused,
                                viewModel,
                                nextRoute,
                                navController
                            )
                        else{
                            KeyboardFunctions.ClearFocus(focusManager, focused)
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))
        }

        item(1){
            PracticeButton(name = "Seguir", enable = actualAnswer.value != "") {
                responseHandler(
                    lifeCycleScope,
                    correctAnswer,
                    focusManager,
                    focused,
                    viewModel,
                    nextRoute,
                    navController
                )
            }

            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
}



private fun responseHandler(
    lifeCycleScope: LifecycleCoroutineScope,
    correctAnswer: String,
    focusManager: FocusManager,
    focused: MutableState<Boolean>,
    viewModel: PracticeViewModel,
    nextRoute: String,
    navController: NavHostController
){
    if(viewModel.VerifyingAnswer(actualAnswer.value, correctAnswer)){
        if(nextRoute != ""){
            navController.navigate(nextRoute)

            viewModel.resetNavRoute()

            viewModel.addIndex()
        }
    }else{
        isIncorrect.value = true
        actualAnswer.value = ""

        lifeCycleScope.launch(Dispatchers.IO){
            delay(600)
            isIncorrect.value = false
        }
    }

    KeyboardFunctions.ClearFocus(focusManager, focused)
}

