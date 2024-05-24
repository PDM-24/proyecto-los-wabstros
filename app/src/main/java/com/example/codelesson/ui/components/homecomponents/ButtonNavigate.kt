package com.example.codelesson.ui.components.homecomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.MoreTransparentWhite
import com.example.codelesson.ui.theme.audioWide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonNavigate(modifier: Modifier, name: String) {
    OutlinedCard(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier,
        border = BorderStroke(2.dp, MoreTransparentWhite),
        onClick = { /*TODO*/ },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontFamily = audioWide
            )
        }
    }
}

@Preview
@Composable
private fun ButtonNavigatePreview() {
    ButtonNavigate(Modifier.fillMaxWidth(), "")
}