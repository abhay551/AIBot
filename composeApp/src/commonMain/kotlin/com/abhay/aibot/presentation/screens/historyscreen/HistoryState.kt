package com.abhay.aibot.presentation.screens.historyscreen

import com.abhay.aibot.ChatMessageEntity
import com.abhay.aibot.base.State

data class HistoryState(
    val messages: List<ChatMessageEntity> = emptyList(),
    val isLoading: Boolean = false
) : State
