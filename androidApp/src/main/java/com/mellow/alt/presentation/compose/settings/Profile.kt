package com.mellow.alt.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

@Composable
fun Profile(viewModel: SwipeViewModel) {
    val userProfile by viewModel.userProfile.observeAsState()
    var size by remember { mutableStateOf(Pair(1, 64.dp)) }

    LazyColumn() {
        items(6) { num ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RectangleShape)
                    .padding(64.dp, 20.dp, 0.dp, 20.dp)
                    .clip(RoundedCornerShape(32.dp, 0.dp, 0.dp, 32.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(32.dp, 0.dp, 0.dp, 32.dp))
                    .height(if (num == size.first) size.second else 64.dp)
                    .clickable {
                        size = Pair(num, 128.dp)
                    }
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = userProfile?.imageList?.get(0)
                            ?: "file:///android_asset/img_temp1.jpg"
                    ),
                    contentDescription = "your main photo",
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(if (num == size.first) size.second.minus(8.dp) else 56.dp))
                        .border(
                            1.dp,
                            Color.Gray,
                            RoundedCornerShape(if (num == size.first) size.second.minus(8.dp) else 56.dp)
                        )
                        .size(if (num == size.first) size.second.minus(8.dp) else 56.dp)
                        .clickable {
                        }
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}