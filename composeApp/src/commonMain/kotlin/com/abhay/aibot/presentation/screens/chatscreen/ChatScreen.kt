package com.abhay.aibot.presentation.screens.chatscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhay.aibot.component.AppBar
import com.abhay.aibot.component.EmptyContent
import com.abhay.aibot.component.Loader
import com.abhay.aibot.component.MessageItem
import com.abhay.aibot.component.UserInputField
import com.abhay.aibot.domain.model.ChatMessage
import com.abhay.aibot.presentation.viewmodel.ChatViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    val viewModel: ChatViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier.background(color = MaterialTheme.colors.background),
        topBar = { AppBar(title = "AI Bot") },
        bottomBar = {
            UserInputField(
                currentMessage = state.currentMessage,
                onMessageChange = { message -> viewModel.sendAction(ChatAction.SendMessage(message)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ChatMessagesList(messages = state.chatMessages, isLoading = state.isLoading)
        }
    }
}


@Composable
fun ChatMessagesList(messages: List<ChatMessage>, isLoading: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
    ) {
        if (messages.isNotEmpty()) {
            items(messages) { message ->
                MessageItem(message = message)
            }
        } else {
            item {
                EmptyContent(
                    modifier = Modifier.fillParentMaxSize(),
                    "Ask your query to AI Bot."
                )
            }
        }
        if (isLoading) {
            item { Loader() }
        }
    }
}


