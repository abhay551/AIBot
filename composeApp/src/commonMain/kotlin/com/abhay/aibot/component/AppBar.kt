package com.abhay.aibot.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(modifier: Modifier = Modifier, title: String) {
    TopAppBar(modifier = modifier) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            style = MaterialTheme.typography.h6
        )
    }
}