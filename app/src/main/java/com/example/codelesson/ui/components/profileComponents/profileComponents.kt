package com.example.codelesson.ui.components.profileComponents


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.FocusedFormWhite
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins


@Composable
fun mainLetters(name : String) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 30.dp),
        color = FocusedFormWhite,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        fontFamily = audioWide

    )
}

@Composable
fun dataLetters(name : String){
    Text(
        color = FocusedFormWhite,
        fontSize = 25.sp,
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
        text = name
    )
}

@Composable
fun secondaryLetters(name : String) {
    Text(
        color = FocusedFormWhite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        fontFamily = audioWide,
        text = name
    )
}



@Composable
@Preview
fun mainLettersPreview() {
    mainLetters("Hello")
}