package com.example.codelesson.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.codelesson.ui.screens.Home
import com.example.codelesson.util.PracticeViewModel
import com.example.codelesson.util.UserViewModel

fun NavGraphBuilder.homeGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    practiceViewModel: PracticeViewModel,
    userViewModel: UserViewModel
) {
    navigation(
        route = Graph.HOME.graph,
        startDestination = HomeGraph.Home.route
    ) {
        composable(HomeGraph.Home.route) {
            Home(innerPadding, navController, practiceViewModel, userViewModel)
        }

        profileGraph(innerPadding, navController, userViewModel)
        quizGraph(innerPadding, navController, practiceViewModel, userViewModel)
    }
}