package com.mellow.alt.presentation.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mellow.alt.R
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

fun NavGraphBuilder.buildTabs(
    viewModel: SwipeViewModel
) {
    composable(NavigationTabs.SWIPE.route) { from ->
        SwipeScreen(viewModel)
    }
    composable(NavigationTabs.LIKED.route) { from ->
        LikedScreen()
    }
    composable(NavigationTabs.MESSAGES.route) { from ->
        Chat(Any(), viewModel = viewModel)
    }

    composable(NavigationTabs.PROFILE.route) { from ->
        Profile(viewModel = viewModel)
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
    LIKED(
        R.string.nav_liked,
        R.drawable.ic_swipe_yes,
        TabsDestinations.LIKED_ROUTE
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
    const val LIKED_ROUTE = "tabs/liked"
}