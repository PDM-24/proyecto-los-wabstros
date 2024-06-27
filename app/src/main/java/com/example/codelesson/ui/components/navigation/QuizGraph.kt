package com.example.codelesson.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.codelesson.ui.screens.LessonRecap
import com.example.codelesson.ui.screens.MainMovingLabel
import com.example.codelesson.ui.screens.MultipleResponse
import com.example.codelesson.ui.screens.ResponseEntry
import com.example.codelesson.ui.screens.Theory
import com.example.codelesson.util.PracticeViewModel
import com.example.codelesson.util.UserViewModel


fun NavGraphBuilder.quizGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    practiceViewModel: PracticeViewModel,
    userViewModel: UserViewModel
) {
    navigation(
        route = Graph.QUIZ.graph,
        startDestination = QuizGraph.Theory.route
    ) {
        composable(QuizGraph.Theory.route) {
            Theory(innerPadding, navController, practiceViewModel)
        }

        composable(QuizGraph.MovingLabel.route) {
            MainMovingLabel(innerPadding = innerPadding, practiceViewModel = practiceViewModel, navController)
        }

        composable(QuizGraph.ResponseEntry.route) {
            ResponseEntry(innerPadding, practiceViewModel, navController)
        }

        composable(QuizGraph.MultipleResponse.route) {
            MultipleResponse(innerPadding, practiceViewModel, navController)
        }

        composable(QuizGraph.LessonRecap.route) {
            LessonRecap(innerPadding, practiceViewModel, userViewModel , navController)
        }
    }
}