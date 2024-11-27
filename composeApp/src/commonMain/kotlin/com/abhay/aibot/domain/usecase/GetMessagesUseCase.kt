package com.abhay.aibot.domain.usecase

import com.abhay.aibot.ChatMessageEntity
import com.abhay.aibot.domain.repository.ChatRepository

class GetMessagesUseCase(private val chatRepository: ChatRepository) {

    suspend operator fun invoke(): List<ChatMessageEntity> {
        return chatRepository.getAllMessages()
    }
}