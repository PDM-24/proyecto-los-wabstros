package com.example.codelesson.ui.components.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.codelesson.ui.screens.CreateAccount
import com.example.codelesson.ui.screens.LogIn
import com.example.codelesson.util.PracticeViewModel
import com.example.codelesson.util.UserViewModel

fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    practiceViewModel : PracticeViewModel
) {
    navigation(
        route = Graph.LOGIN.graph,
        startDestination = LoginGraph.Login.route
    ) {
        composable(LoginGraph.Login.route) {
            LogIn(userViewModel, practiceViewModel, navController)
        }

        composable(LoginGraph.SignOn.route) {
            CreateAccount(userViewModel, navController)
        }
    }
}