package com.mellow.alt.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

@Composable
fun AltApp(viewModel: SwipeViewModel) {
    val tabs = remember { NavigationTabs.values() }
    val navController = rememberNavController()

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
            buildTabs(viewModel = viewModel)
        }
    }
}