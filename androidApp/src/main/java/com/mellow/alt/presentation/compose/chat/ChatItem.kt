package com.mellow.alt.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

@Composable
fun ChatItem(viewModel: SwipeViewModel,navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                navController.navigate("tabs/chat")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "file:///android_asset/img_temp2.jpg"),
            contentDescription = "chat_photo",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(64.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(12.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxHeight()
        ) {

            Text(text = "Name")
            Text(text = "last message")
        }
    }
}