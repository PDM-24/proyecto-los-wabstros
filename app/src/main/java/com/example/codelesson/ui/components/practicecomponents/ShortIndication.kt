package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.R
import com.example.codelesson.ui.theme.poppins

@Composable
fun ShortIndication(indication: String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                bottom = 30.dp,
                start = 45.dp,
                end = 25.dp
            )
    ){
        Icon(
            imageVector = ImageVector.vectorResource(
                R.drawable.arrow_indication
            ),
            contentDescription = "arrow-right",
            tint = Color.White
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = indication.uppercase(),
            color = Color.White,
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}