package com.example.codelesson.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.codelesson.ui.components.navigation.HomeGraph

data class DataHome(
    val name: String,
    val topic: String
)

fun getName(): List<DataHome> {
    return listOf(
        DataHome(
            "GET STARTED",
            "nosebro :("
        ),
        DataHome(
            "SINTAXIS",
            "Syntax"
        ),
        DataHome(
            "SALIDA DE DATOS",
            "Salida de datos"
        ),
        DataHome(
            "COMENTARIOS",
            "Comentarios"
        ),
        DataHome(
            "VARIABLES",
            "Variables"
        ),
        DataHome(
            "ENTRADA DE DATOS",
            "Entrada de datos"
        ),
        DataHome(
            "TIPOS DE DATOS",
            "Tipos de datos????"
        ),
        DataHome(
            "OPERADORES",
            "Operadores????"
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
            HomeGraph.Profile.route
        )
    )
}