package com.mellow.alt.presentation.compose

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MessageItem(text: String, isYourMessage: Boolean, isRead: Boolean) {
    if (isYourMessage) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(modifier = Modifier.width(IntrinsicSize.Max), text = text)
        }
    } else {
        Row {
            Text(modifier = Modifier.width(IntrinsicSize.Max), text = text)
            Spacer(modifier = Modifier.weight(1f))
        }
    }

}