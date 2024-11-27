package com.abhay.aibot.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyContent(modifier: Modifier = Modifier, text: String?) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = text?:"No Data")
    }
}