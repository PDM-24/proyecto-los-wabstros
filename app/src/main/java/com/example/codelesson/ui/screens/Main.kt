@file:Suppress("UNUSED_EXPRESSION")

package com.example.codelesson.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codelesson.data.BottomNavItems
import com.example.codelesson.data.NavItemsList
import com.example.codelesson.ui.components.homecomponents.TopBar
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.navigation.NavBarGraph
import com.example.codelesson.ui.theme.DividerPurple
import com.example.codelesson.ui.theme.LessTransparentWhite
import com.example.codelesson.ui.theme.NeonGreen
import com.example.codelesson.ui.theme.TopBarGrey

@Composable
fun Main() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route

    fun setTitle(currentRoute: String?) : String {
        return when(currentRoute) {
            HomeGraph.Home.route -> "Desarrollador C++"
            else -> HomeGraph.Profile.route
        }
    }

    Scaffold(topBar = {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(TopBarGrey)
                .padding(24.dp),
            setTitle(currentRoute)
        )
    }, bottomBar = {
        Column {
            if (currentRoute != HomeGraph.Theory.route) {
                Divider(thickness = 2.dp, color = DividerPurple)
                BottomBar(
                    currentRoute,
                ) { bottomNavItems ->
                    navController.navigate(bottomNavItems.route) {
                        navController.graph.startDestinationRoute?.let { startDestinationRoute ->
                            popUpTo(startDestinationRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }) { InnerPadding ->
        NavBarGraph(InnerPadding, navController)
    }
}

@Composable
fun BottomBar(
    currentRoute: String?,
    onClick: (BottomNavItems) -> Unit
) {
    NavigationBar {
        NavItemsList().forEachIndexed { index, navItems ->
            NavigationBarItem(
                selected = currentRoute == navItems.route,
                onClick = { onClick(navItems) },
                icon = {
                    Icon(
                        imageVector =
                        if (currentRoute == navItems.route)
                            navItems.iconSelected
                        else
                            navItems.iconDefault,
                        contentDescription = navItems.description,
                        tint =
                        if (currentRoute == navItems.route) {
                            NeonGreen
                        }
                        else {
                            LessTransparentWhite
                        }
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    Main()
}