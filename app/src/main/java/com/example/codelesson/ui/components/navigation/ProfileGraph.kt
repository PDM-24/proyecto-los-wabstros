package com.example.codelesson.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.codelesson.ui.screens.EditProfile
import com.example.codelesson.ui.screens.Profile
import com.example.codelesson.ui.screens.Security
import com.example.codelesson.util.UserViewModel

fun NavGraphBuilder.profileGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    navigation(
        route = Graph.PROFILE.graph,
        startDestination = ProfileGraph.Profile.route
    ) {
        composable(ProfileGraph.Profile.route) {
            Profile(innerPadding, userViewModel, navController)
        }

        composable(ProfileGraph.EditProfile.route) {
            EditProfile(innerPadding, userViewModel)
        }

        composable(ProfileGraph.Security.route) {
            Security(innerPadding, userViewModel)
        }
    }
}