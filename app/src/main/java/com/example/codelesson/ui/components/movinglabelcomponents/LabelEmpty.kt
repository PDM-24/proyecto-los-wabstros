package com.example.codelesson.ui.components.movinglabelcomponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun LabelEmpty() {
    var d = LocalDensity.current
    OutlinedCard(
        modifier = Modifier
            .width(135.dp)
            .height(44.dp)
    ) {

    }
}