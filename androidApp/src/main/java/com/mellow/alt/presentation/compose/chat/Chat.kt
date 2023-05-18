package com.mellow.alt.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mellow.alt.R
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel

@Composable
fun Chat(viewModel: SwipeViewModel) {
    var message by rememberSaveable { mutableStateOf("Text") }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(/*personChat.person.name*/"file:///android_asset/img_temp.jpg"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(48.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(48.dp)),
                    contentScale = ContentScale.Fit,
                )
                Text("Vika")
            }
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                reverseLayout = true
            ) {
                var isYourMessage = true
                items(124) {
                    isYourMessage = !isYourMessage
                    MessageItem(text = "sosi huy", isYourMessage = isYourMessage, isRead = true)
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                TextField(
                    value = message,
                    onValueChange = {
                        message = it
                    },
                    modifier = Modifier
                        .weight(1f)
                )
                Button(modifier = Modifier.size(64.dp), onClick = {
                    viewModel.sendMessage(message)
                    viewModel.checkServer()
                    message = ""

                }) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.img_temp),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    )
}