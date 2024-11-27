package com.abhay.aibot.domain.usecase

import com.abhay.aibot.domain.repository.ChatRepository

class GenerateResponseUseCase(private val chatRepository: ChatRepository) {

    suspend operator fun invoke(prompt: String): String {
        val response = try {
            chatRepository.generateContent(prompt)
        } catch (e: Exception) {
            "Oops something went wrong!"
        }
        return response
    }
}