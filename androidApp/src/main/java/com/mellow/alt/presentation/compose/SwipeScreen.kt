package com.mellow.alt.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel
import com.mellow.alt.utils.SwipeCardNum

@Composable
fun SwipeScreen(viewModel: SwipeViewModel) {

    Box() {
        SwipeCard(viewModel = viewModel, cardNum = SwipeCardNum.FIRST)
        SwipeCard(viewModel = viewModel, cardNum = SwipeCardNum.SECOND)
    }
}