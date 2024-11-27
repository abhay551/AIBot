package com.abhay.aibot.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.abhay.aibot.base.BaseViewModel
import com.abhay.aibot.domain.model.ChatMessage
import com.abhay.aibot.domain.usecase.GenerateResponseUseCase
import com.abhay.aibot.domain.usecase.SaveMessageUseCase
import com.abhay.aibot.presentation.screens.chatscreen.ChatAction
import com.abhay.aibot.presentation.screens.chatscreen.ChatState
import com.abhay.aibot.presentation.screens.chatscreen.ChatViewEvent

import kotlinx.coroutines.launch

class ChatViewModel(
    private val generateResponseUseCase: GenerateResponseUseCase,
    private val saveMessageUseCase: SaveMessageUseCase
) : BaseViewModel<ChatState, ChatAction, ChatViewEvent>(ChatState()) {

    override fun reduceState(currentState: ChatState, action: ChatAction): ChatState {
        return when (action) {
            is ChatAction.SendMessage -> {
                currentState.copy(
                    chatMessages = currentState.chatMessages + ChatMessage(
                        text = action.message,
                        isUserMessage = true
                    ),
                    currentMessage = "",
                    isLoading = true
                )
            }
        }
    }

    override fun handleSideEffect(action: ChatAction, newState: ChatState) {
        when (action) {
            is ChatAction.SendMessage -> handleBotResponse(action.message, newState)
        }
    }

    private fun handleBotResponse(userMessage: String, currentState: ChatState) {
        setState(currentState.copy(isLoading = true))
        viewModelScope.launch {
            val botResponse = generateResponseUseCase(userMessage)
            val chatMessage = ChatMessage(text = botResponse, isUserMessage = false)
            saveMessageUseCase.invoke(prompt = userMessage, response = botResponse)
            setState(
                currentState.copy(
                    isLoading = false,
                    chatMessages = currentState.chatMessages + chatMessage
                )
            )
        }
    }
}

