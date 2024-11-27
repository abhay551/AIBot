package com.abhay.aibot.domain.repository

import com.abhay.aibot.ChatMessageEntity

interface ChatRepository {
    suspend fun generateContent(prompt: String): String
    suspend fun insertMessage(message: ChatMessageEntity)
    suspend fun getAllMessages(): List<ChatMessageEntity>
    suspend fun clearAllMessages()
}