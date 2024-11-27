package com.abhay.aibot.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun UserInputField(
    currentMessage: String,
    onMessageChange: (String) -> Unit
) {
    var userMessage by remember { mutableStateOf(currentMessage) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = userMessage,
            onValueChange = { newText ->
                userMessage = newText
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (userMessage.isNotEmpty()) {
                        onMessageChange(userMessage)
                        userMessage = ""
                    }
                }
            ),
            modifier = Modifier
                .weight(1f)
                .background(
                    MaterialTheme.colors.onBackground.copy(alpha = 0.2f),
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp)
        )
        IconButton(onClick = {
            if (userMessage.isNotEmpty()) {
                onMessageChange(userMessage)
                userMessage = ""
            }
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Send,
                contentDescription = "Icon",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}