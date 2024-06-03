package com.example.codelesson.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val actualAnswer: MutableState<String> = mutableStateOf("")
private val isIncorrect: MutableState<Boolean> = mutableStateOf(false)

@Composable
fun ResponseEntry (innerPadding: PaddingValues){
    val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope

    val focused = remember {
        mutableStateOf(false)
    }

    val imeState = rememberImeState()
    val scrollState = rememberLazyListState()

    val focusManager = LocalFocusManager.current
    val correctAnswer = "<="

    val animatedColorContainer = animateColorAsState(
        targetValue = if(isIncorrect.value) Red else FormWhite,
        animationSpec = tween(200, 0, LinearEasing)
    )

    LaunchedEffect(imeState.value) {
        if(imeState.value){
            focused.value = true
            delay(150)
            scrollState.animateScrollToItem(1)
        }
    }

    BackHandler(focused.value) {
        ClearFocus(focusManager, focused)
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
                ClearFocus(focusManager, focused)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState
    ) {
        item(0) {
            Hint(hint = "El operador de lógico > permite saber cuando un número es mayor o igual a otro.",
                isIncorrect = isIncorrect.value)

            ShortIndication(indication = "Encuentra el error")

            DetailedIndication(indication = "validacion para entrar al if si n es mayor que 3")

            CodeBlock(code = "if(n <= 3){\n" +
                    "\n" +
                    "...\n" +
                    "\n" +
                    "}")

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
                            responseHandler(lifeCycleScope, correctAnswer, focusManager, focused)
                        else{
                            ClearFocus(focusManager, focused)
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))
        }

        item(1){
            PracticeButton(name = "Seguir", enable = actualAnswer.value != "") {
                responseHandler(lifeCycleScope, correctAnswer, focusManager, focused)
            }

            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
}

private fun VerifyingAnswer(answer: String, correctAnswer: String) =
    answer == correctAnswer

private fun responseHandler(
    lifeCycleScope: LifecycleCoroutineScope,
    correctAnswer: String,
    focusManager: FocusManager,
    focused: MutableState<Boolean>
){
    if(VerifyingAnswer(actualAnswer.value, correctAnswer)){
        /* TODO: navigation */
    }else{
        isIncorrect.value = true
        actualAnswer.value = ""

        lifeCycleScope.launch(Dispatchers.IO){
            delay(600)
            isIncorrect.value = false
        }
    }

    ClearFocus(focusManager, focused)
}

private fun ClearFocus(focusManager: FocusManager, focused: MutableState<Boolean>){
    focusManager.clearFocus()
    focused.value = false
}