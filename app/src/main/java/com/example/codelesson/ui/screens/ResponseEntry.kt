package com.example.codelesson.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.components.practicecomponents.DetailedIndication
import com.example.codelesson.ui.components.practicecomponents.Hint
import com.example.codelesson.ui.components.practicecomponents.PracticeButton
import com.example.codelesson.ui.components.practicecomponents.ShortIndication
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.FocusedFormWhite
import com.example.codelesson.ui.theme.FormWhite
import com.example.codelesson.ui.theme.LetterTransparentBlack
import com.example.codelesson.ui.theme.TransparentWhite
import com.example.codelesson.ui.theme.poppins

@Composable
fun ResponseEntry (innerPadding: PaddingValues){
    val response = remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current


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
                focusManager.clearFocus()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Hint(hint = "El operador de lógico > permite saber cuando un número es mayor o igual a otro.")

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
                value = response.value,
                onValueChange = {
                    response.value = it
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
                    unfocusedContainerColor = FormWhite,
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
                    onAny = { focusManager.clearFocus() }
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))

            PracticeButton(name = "Seguir", enable = false) {

            }
        }
    }
}