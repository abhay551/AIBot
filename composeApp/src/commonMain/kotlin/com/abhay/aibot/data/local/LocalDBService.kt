package com.abhay.aibot.data.local

import com.abhay.aibot.ChatMessageEntity

interface LocalDBService {
    suspend fun insertMessage(message: ChatMessageEntity)
    suspend fun getAllMessages(): List<ChatMessageEntity>
    suspend fun clearAllMessages()
}