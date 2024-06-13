package com.example.codelesson.ui.components.navigation

sealed class Graph(val graph: String) {
    data object ROOT : Graph("ROOT")
    data object LOGIN : Graph("LOGIN")
    data object PROFILE : Graph("PROFILE")
    data object HOME : Graph("HOME")
    data object QUIZ : Graph("QUIZ")
}

sealed class LoginGraph(val route: String) {
    data object Login: LoginGraph("Login")
    data object SignOn: LoginGraph("SignOn")
}

sealed class HomeGraph(val route: String) {
    data object Home: HomeGraph("Home")
}

sealed class ProfileGraph(val route: String) {
    data object Profile: HomeGraph("Profile")
    data object Security: ProfileGraph("Security")
    data object EditProfile: ProfileGraph("EditProfile")
}

sealed class QuizGraph(val route: String) {
    data object Theory: QuizGraph("Theory")
    data object MultipleResponse: QuizGraph("MultipleResponse")
    data object MovingLabel: QuizGraph("MovingLabel")
    data object ResponseEntry: QuizGraph("ResponseEntry")
    data object LessonRecap: QuizGraph("LessonRecap")
}