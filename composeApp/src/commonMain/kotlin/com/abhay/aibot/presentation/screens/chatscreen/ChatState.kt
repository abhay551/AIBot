package com.abhay.aibot.presentation.screens.chatscreen

import com.abhay.aibot.base.State
import com.abhay.aibot.domain.model.ChatMessage

data class ChatState(
    val chatMessages: List<ChatMessage> = emptyList(),
    val currentMessage: String = "",
    val isLoading: Boolean = false
) : State