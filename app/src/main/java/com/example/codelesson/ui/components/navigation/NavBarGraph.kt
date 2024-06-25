package com.example.codelesson.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.codelesson.util.PracticeViewModel
import com.example.codelesson.util.UserViewModel

@Composable
fun NavBarGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    practiceViewModel: PracticeViewModel,
    userViewModel: UserViewModel
    ) {
    NavHost(
        navController = navController,
        route = Graph.ROOT.graph,
        startDestination = Graph.LOGIN.graph
    ) {
        loginGraph(navController, userViewModel, practiceViewModel)
        homeGraph(innerPadding,navController ,practiceViewModel, userViewModel)
    }
}