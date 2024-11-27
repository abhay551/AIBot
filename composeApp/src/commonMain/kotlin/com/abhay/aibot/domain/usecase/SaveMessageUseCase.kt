package com.abhay.aibot.domain.usecase

import com.abhay.aibot.ChatMessageEntity
import com.abhay.aibot.di.currentTimeMillis
import com.abhay.aibot.di.generateUUID
import com.abhay.aibot.domain.repository.ChatRepository

class SaveMessageUseCase(private val chatRepository: ChatRepository) {

    suspend operator fun invoke(prompt: String, response: String) {
        return chatRepository.insertMessage(
            ChatMessageEntity(
                id = generateUUID(),
                prompt = prompt,
                response = response,
                timestamp = currentTimeMillis()
            )
        )
    }
}