package com.example.codelesson.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.navigation.ProfileGraph

data class DataHome(
    val name: String
)

fun getName(): List<DataHome> {
    return listOf(
        DataHome(
            "Estructura básica",
        ),
        DataHome(
            "Sintaxis"
        ),
        DataHome(
            "Output"
        ),
        DataHome(
            "Comentarios"
        ),
        DataHome(
            "Tipos de variables"
        ),
        DataHome(
            "Inputs"
        ),
        DataHome(
            "Operadores"
        ),
        DataHome(
            "Strings"
        ),
        DataHome(
            "Booleanos"
        ),
        DataHome(
            "Condiciones"
        )
    )
}

data class BottomNavItems(
    val title: String,
    val description: String,
    val iconDefault: ImageVector,
    val iconSelected: ImageVector,
    val route: String,
)

fun navItemsList(): List<BottomNavItems> {
    return listOf(
        BottomNavItems
            (
            "Desarrollador C++",
            "Home",
            Icons.Outlined.Home,
            Icons.Filled.Home,
            HomeGraph.Home.route
        ),
        BottomNavItems(
            "Profile",
            "Profile",
            Icons.Outlined.Person,
            Icons.Filled.Person,
            ProfileGraph.Profile.route
        )
    )
}