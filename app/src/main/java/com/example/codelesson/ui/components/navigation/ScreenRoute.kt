package com.example.codelesson.ui.components.navigation

sealed class Graph(val graph: String) {
    data object LOGIN : Graph("LOGIN")
    data object HOME : Graph("HOME")
}

sealed class LoginGraph(val route: String) {
    data object Login: LoginGraph("Login")
    data object SignOn: LoginGraph("SignOn")
}

sealed class HomeGraph(val route: String) {
    data object Home: HomeGraph("Home")
    data object Theory: HomeGraph("Theory")
}