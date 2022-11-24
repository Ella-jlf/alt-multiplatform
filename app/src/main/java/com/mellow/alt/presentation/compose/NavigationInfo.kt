package com.mellow.alt.presentation.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mellow.alt.R

fun NavGraphBuilder.buildTabs() {
    composable(NavigationTabs.SWIPE.route) { from ->
        SwipeScreen()
    }
    composable(NavigationTabs.MESSAGES.route) { from ->
        Text("sosi2")
    }

    composable(NavigationTabs.PROFILE.route) { from ->
        Text("Sosi1")
    }
}

enum class NavigationTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    SWIPE(
        R.string.nav_swipe,
        R.drawable.ic_swipe_nope,
        TabsDestinations.MESSAGES_ROUTE
    ),
    MESSAGES(
        R.string.nav_messages,
        R.drawable.ic_swipe_yes,
        TabsDestinations.SWIPE_ROUTE
    ),
    PROFILE(
        R.string.nav_profile,
        R.drawable.ic_swipe_nope,
        TabsDestinations.PROFILE_ROUTE
    )
}


private object TabsDestinations {
    const val SWIPE_ROUTE = "tabs/swipe"
    const val MESSAGES_ROUTE = "tabs/messages"
    const val PROFILE_ROUTE = "tabs/profile"
}