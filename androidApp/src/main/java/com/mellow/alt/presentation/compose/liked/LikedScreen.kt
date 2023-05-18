package com.mellow.alt.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LikedScreen() {
    val sosi = listOf("a", "b", "c", "d", "f", "g", "k")
    Column() {
        Spacer(modifier = Modifier.weight(1f))
        LazyRow(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 32.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(sosi) {
                SmallSwipeCard()
            }
        }
    }
}