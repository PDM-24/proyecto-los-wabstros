package com.example.codelesson.ui.screens

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codelesson.data.BottomNavItems
import com.example.codelesson.data.navItemsList
import com.example.codelesson.ui.components.homecomponents.TopBar
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.navigation.NavBarGraph
import com.example.codelesson.ui.components.navigation.QuizGraph
import com.example.codelesson.ui.theme.DividerPurple
import com.example.codelesson.ui.theme.LessTransparentWhite
import com.example.codelesson.ui.theme.NeonGreen
import com.example.codelesson.ui.theme.TopBarGrey
import com.example.codelesson.util.HomeViewModel


@Composable
fun MainScreen() {
    val viewModel: HomeViewModel = viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route

    val title = viewModel.titleTopBar.collectAsState()
    val topBarTitle = if (currentRoute == HomeGraph.Home.route) "Desarrollador C++" else title.value

    Scaffold(topBar = {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(TopBarGrey)
                .padding(24.dp),
            topBarTitle
        )
    }, bottomBar = {
        Column {
            if (currentRoute != QuizGraph.Theory.route) {
                Divider(thickness = 2.dp, color = DividerPurple)
                BottomBar(
                    currentRoute,
                ) { bottomNavItems ->
                    navController.navigate(bottomNavItems.route) {
                        viewModel.setTitle(bottomNavItems.title)
                        Log.d("DEBUG", bottomNavItems.title)
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
    }) { innerPadding ->
        NavBarGraph(innerPadding, navController)
    }
}

@Composable
fun BottomBar(
    currentRoute: String?,
    onClick: (BottomNavItems) -> Unit
) {
    NavigationBar {
        navItemsList().forEachIndexed { _, navItems ->
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
                        } else {
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
    MainScreen()
}