package com.example.codelesson.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.codelesson.ui.components.movinglabelcomponents.DragableScreen
import com.example.codelesson.util.PracticeViewModel

@Composable
fun MainMovingLabel(innerPadding: PaddingValues, practiceViewModel: PracticeViewModel) {
    DragableScreen {
        MovingLabels(
            innerPadding = innerPadding,
            practiceViewModel = practiceViewModel
        )
    }
}