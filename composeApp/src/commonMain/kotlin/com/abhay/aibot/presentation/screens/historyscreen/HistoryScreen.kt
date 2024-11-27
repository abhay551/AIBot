package com.abhay.aibot.presentation.screens.historyscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhay.aibot.ChatMessageEntity
import com.abhay.aibot.component.AppBar
import com.abhay.aibot.component.EmptyContent
import com.abhay.aibot.presentation.viewmodel.HistoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HistoryViewModel>()
    val state = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onLoadMessages()
    }

    Scaffold(
        modifier = modifier.background(MaterialTheme.colors.background),
        topBar = { AppBar(title = "History") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(paddingValues)
        ) {
            if (state.messages.isNotEmpty()) {
                items(state.messages) { message ->
                    MessageItem(chatMessage = message)
                }
            } else {
                item {
                    EmptyContent(
                        modifier = Modifier.fillParentMaxSize(),
                        text = "No data available"
                    )
                }
            }
        }
    }
}

@Composable
private fun MessageItem(chatMessage: ChatMessageEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(4.dp)
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = chatMessage.prompt,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = chatMessage.response,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}
