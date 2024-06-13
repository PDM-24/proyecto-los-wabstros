package com.example.codelesson.ui.components.profileComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.FocusedFormWhite


@Composable
fun mainLetters(name : String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp),
        color = FocusedFormWhite,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        text = name
    )
}

@Composable
@Preview
fun mainLettersPreview() {
    mainLetters("Hello")
}