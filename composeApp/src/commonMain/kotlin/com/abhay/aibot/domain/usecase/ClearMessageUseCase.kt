package com.abhay.aibot.domain.usecase

import com.abhay.aibot.domain.repository.ChatRepository

class ClearMessageUseCase(private val chatRepository: ChatRepository) {

    suspend operator fun invoke() {
        chatRepository.clearAllMessages()
    }
}