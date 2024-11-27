package com.abhay.aibot.data.local

import com.abhay.aibot.ChatMessageEntity

class LocalDBServiceImpl(private val appDatabase: SharedAppDatabase) : LocalDBService {
    override suspend fun insertMessage(message: ChatMessageEntity) {
        appDatabase.getAppDBQueries().insertMessage(
            id = message.id,
            prompt = message.prompt,
            response = message.response,
            timestamp = message.timestamp
        )
    }

    override suspend fun getAllMessages(): List<ChatMessageEntity> {
        return appDatabase.getAppDBQueries().selectAllMessages().executeAsList().map {
            ChatMessageEntity(
                id = it.id,
                prompt = it.prompt,
                response = it.response,
                timestamp = it.timestamp
            )
        }
    }

    override suspend fun clearAllMessages() {
        appDatabase.getAppDBQueries().clearAllMessages()
    }
}