package com.example.codelesson.ui.components.logincomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.poppins

@Composable
fun SignupTextField(
    onValueChange: (String) -> Unit,
    hint: String,
    value: String,
){

    val containerColor = Color(0xFF8E8E8E)
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    color = Color(0xFFFFFDFD),
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .clip(RoundedCornerShape(percent = 25)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            cursorColor = Color(0xFFFFFDFD),
            focusedIndicatorColor = Color(0xFFFFFDFD),
            unfocusedIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            color = Color(0xFFFFFDFD)
        )
    )
}
