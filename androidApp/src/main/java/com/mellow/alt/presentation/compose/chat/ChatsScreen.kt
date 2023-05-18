package com.mellow.alt.presentation.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

@Composable
fun ChatsScreen(viewModel: SwipeViewModel,navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(32) {
            ChatItem(viewModel = viewModel, navController = navController)
        }
    }
}