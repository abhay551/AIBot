package com.abhay.aibot.presentation.screens.chatscreen

import com.abhay.aibot.base.Action

sealed class ChatAction : Action {
    data class SendMessage(val message: String) : ChatAction()
}