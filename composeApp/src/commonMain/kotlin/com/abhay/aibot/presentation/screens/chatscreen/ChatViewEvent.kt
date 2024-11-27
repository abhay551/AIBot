package com.abhay.aibot.presentation.screens.chatscreen

import com.abhay.aibot.base.Event

sealed class ChatViewEvent : Event {
    data class ShowError(val message: String) : ChatViewEvent()
}