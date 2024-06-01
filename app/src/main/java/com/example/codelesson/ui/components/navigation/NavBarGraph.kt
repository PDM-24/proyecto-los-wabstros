package com.example.codelesson.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.codelesson.ui.screens.Home
import com.example.codelesson.ui.screens.Profile
import com.example.codelesson.ui.screens.Theory
import com.example.codelesson.util.HomeViewModel

@Composable
fun NavBarGraph(innerPadding: PaddingValues, navController: NavHostController) {
    val viewModel = viewModel<HomeViewModel>()

    NavHost(
        navController = navController,
        route = Graph.HOME.graph,
        startDestination = HomeGraph.Home.route
    ) {
        composable(HomeGraph.Home.route) {
//            val viewModel = it.sharedViewModel<HomeViewModel>(navController)
            Home(innerPadding, navController, viewModel)
        }

        composable(HomeGraph.Profile.route) {
//            val viewModel = it.sharedViewModel<HomeViewModel>(navController)
            Profile(innerPadding)
        }

        navigation(
            route = "${Graph.QUIZ.graph}/{title}",
            startDestination = QuizGraph.Theory.route,
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            composable(
                QuizGraph.Theory.route,
            ) { entry ->
                val parentEntry =
                    remember(entry) {
                        navController.getBackStackEntry("${Graph.QUIZ.graph}/{title}")
                    }
                val title = parentEntry.arguments?.getString("title")
//                val viewModel = entry.sharedViewModel<HomeViewModel>(navController)
                Theory(innerPadding, navController, viewModel, title)
            }
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
