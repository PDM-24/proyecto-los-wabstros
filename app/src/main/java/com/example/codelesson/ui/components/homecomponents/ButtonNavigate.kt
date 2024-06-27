package com.example.codelesson.ui.components.homecomponents

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.theme.MoreTransparentWhite
import com.example.codelesson.ui.theme.NeonGreen
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.util.PracticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonNavigate(
    modifier: Modifier,
    name: String,
    viewModel: PracticeViewModel,
    onClick: () -> Unit,
) {
    val status = remember {
        mutableStateOf(false)
    }

    val list by viewModel.lessonStatus.collectAsState()

    LaunchedEffect(list) {
        list.forEach {
            if (it.title == name.uppercase() && it.complete.value) {
                status.value = true
                Log.d("Boton", status.value.toString())
            }
        }
    }

    OutlinedCard(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier,
        border =
        if (status.value) {
            BorderStroke(2.dp, NeonGreen)
        } else {
            BorderStroke(2.dp, MoreTransparentWhite)
        },
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontFamily = audioWide,
                color =
                if (status.value) {
                    NeonGreen
                } else {
                    MoreTransparentWhite
                }
            )
        }
    }
}