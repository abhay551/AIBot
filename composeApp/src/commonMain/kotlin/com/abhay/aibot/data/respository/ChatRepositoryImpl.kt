package com.abhay.aibot.data.respository

import com.abhay.aibot.ChatMessageEntity
import com.abhay.aibot.data.api.GeminiApiService
import com.abhay.aibot.data.local.LocalDBService
import com.abhay.aibot.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val geminiApiService: GeminiApiService,
    private val localDBService: LocalDBService
) : ChatRepository {

    override suspend fun generateContent(prompt: String): String {
        return geminiApiService.generateResponse(prompt)
    }

    override suspend fun insertMessage(message: ChatMessageEntity) {
        localDBService.insertMessage(message)
    }

    override suspend fun getAllMessages(): List<ChatMessageEntity> {
        return localDBService.getAllMessages()
    }

    override suspend fun clearAllMessages() {
        localDBService.clearAllMessages()
    }

}