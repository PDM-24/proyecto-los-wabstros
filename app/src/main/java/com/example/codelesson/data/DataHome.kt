package com.example.codelesson.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.codelesson.ui.components.navigation.HomeGraph

data class DataHome(
    val name: String
)

fun getName(): List<DataHome> {
    return listOf(
        DataHome(
            "GET STARTED"
        ),
        DataHome(
            "SINTAXIS"
        ),
        DataHome(
            "SALIDA DE DATOS"
        ),
        DataHome(
            "COMENTARIOS"
        ),
        DataHome(
            "VARIABLES"
        ),
        DataHome(
            "ENTRADA DE DATOS"
        ),
        DataHome(
            "TIPOS DE DATOS"
        ),
        DataHome(
            "OPERADORES"
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

fun NavItemsList(): List<BottomNavItems> {
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
            HomeGraph.Profile.route
        )
    )
}