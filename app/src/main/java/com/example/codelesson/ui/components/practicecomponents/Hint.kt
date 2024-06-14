package com.example.codelesson.ui.components.practicecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.R
import com.example.codelesson.ui.components.NeonIcon
import com.example.codelesson.ui.theme.MoreTransparentWhite
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins

@Composable
fun Hint(hint: String, isIncorrect: Boolean){
    Column (
        modifier = Modifier
            .background(Color.Transparent)
            .padding(top = 75.dp)
    ) {
        Column (
            modifier = Modifier
                .background(Color.Black)
                .heightIn(min = 150.dp)
                .fillMaxWidth()
                .padding(
                    top = 13.dp,
                    bottom = 25.dp
                )
                .padding(horizontal = 15.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 27.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(!isIncorrect){
                    Icon(
                        modifier = Modifier
                            .size(27.dp),
                        imageVector = ImageVector.vectorResource(
                            R.drawable.lightbulb_outline
                        ),
                        contentDescription = "lightbulb-off",
                        tint = MoreTransparentWhite
                    )
                }else{
                    NeonIcon(
                        drawable = ImageVector.vectorResource(R.drawable.lightbulb_filled),
                        tint = Color.White,
                        modifier = Modifier
                            .size(21.dp),
                        shadowRadius = 5.dp
                    )
                }

            }
            Text(
                modifier = Modifier
                    .padding(top = 7.dp)
                    .padding(horizontal = 15.dp),
                text = hint.uppercase(),
                color = Color.White,
                fontFamily = poppins,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
