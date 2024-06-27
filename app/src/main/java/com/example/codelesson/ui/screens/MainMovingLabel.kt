package com.example.codelesson.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.movinglabelcomponents.DragableScreen
import com.example.codelesson.util.PracticeViewModel

@Composable
fun MainMovingLabel(
    innerPadding: PaddingValues,
    practiceViewModel: PracticeViewModel,
    navController: NavHostController
) {
    DragableScreen {
        MovingLabels(
            innerPadding = innerPadding,
            viewModel = practiceViewModel,
            navController = navController
        )
    }
}