package com.mellow.alt.presentation.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mellow.alt.presentation.compose.navigation.AltBottomBar
import com.mellow.alt.presentation.compose.navigation.NavigationTabs
import com.mellow.alt.presentation.compose.navigation.buildTabs
import com.mellow.alt.presentation.compose.theme.AltMainTheme
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

@Composable
fun AltApp(viewModel: SwipeViewModel) {
    val tabs = remember { NavigationTabs.values() }
    val navController = rememberNavController()


    AltMainTheme {

        Scaffold(
            bottomBar = {
                AltBottomBar(navController = navController, tabs = tabs)
            }
        ) { innerPaddingModifier ->

            NavHost(
                modifier = Modifier.padding(innerPaddingModifier),
                navController = navController,
                startDestination = NavigationTabs.SWIPE.route
            ) {
                buildTabs(viewModel = viewModel, navController)
            }
        }
    }
}