package com.abhay.aibot.data.api

interface GeminiApiService {
    suspend fun generateResponse(prompt: String): String
}