package com.abhay.aibot.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhay.aibot.domain.model.ChatMessage

@Composable
fun MessageItem(message: ChatMessage) {
    val cardShape = if (message.isUserMessage) {
        RoundedCornerShape(
            16.dp, 16.dp, 0.dp, 16.dp
        )
    } else {
        RoundedCornerShape(
            16.dp, 16.dp, 16.dp, 0.dp
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isUserMessage) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
                    shape = cardShape
                )
                .padding(12.dp)
        ) {
            Text(
                message.text,
                color = if (message.isUserMessage) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary
            )
        }
    }
}