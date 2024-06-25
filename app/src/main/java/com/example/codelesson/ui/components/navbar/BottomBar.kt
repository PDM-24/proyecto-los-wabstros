package com.example.codelesson.ui.components.navbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.example.codelesson.data.BottomNavItems
import com.example.codelesson.data.navItemsList
import com.example.codelesson.ui.theme.LessTransparentWhite
import com.example.codelesson.ui.theme.NeonGreen

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