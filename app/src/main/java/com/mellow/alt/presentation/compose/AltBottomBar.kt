package com.mellow.alt.presentation.compose

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import java.util.*

@Composable
fun AltBottomBar(navController: NavController, tabs: Array<NavigationTabs>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: NavigationTabs.SWIPE

    val routes = remember { NavigationTabs.values().map { it.route } }
    if (currentRoute in routes) {
        BottomNavigation() {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                    label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = LocalContentColor.current,
                )
            }
        }
    }
}