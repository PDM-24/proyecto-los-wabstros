package com.example.codelesson.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codelesson.ui.screens.Home
import com.example.codelesson.ui.screens.Profile
import com.example.codelesson.ui.screens.Theory

@Composable
fun NavBarGraph(innerPadding: PaddingValues, navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME.graph,
        startDestination = HomeGraph.Home.route
    ) {
        composable(HomeGraph.Home.route) {
            Home(innerPadding, navController)
        }

        composable(HomeGraph.Profile.route) {
            Profile(innerPadding)
        }

        composable(HomeGraph.Theory.route) {
            Theory(innerPadding)
        }
    }
}