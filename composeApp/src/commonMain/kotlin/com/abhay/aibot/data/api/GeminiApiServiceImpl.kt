package com.abhay.aibot.data.api

import com.abhay.aibot.data.api.model.RequestContent
import com.abhay.aibot.data.api.model.RequestModel
import com.abhay.aibot.data.api.model.RequestPart
import com.abhay.aibot.data.api.model.ResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GeminiApiServiceImpl(private val httpClient: HttpClient) : GeminiApiService {

    override suspend fun generateResponse(prompt: String): String {
        val parts = mutableListOf<RequestPart>()
        parts.add(RequestPart(text = prompt))
        val requestBody = RequestModel(contents = listOf(RequestContent(parts = parts)))
        val apiUrl =
            "$BASE_URL/gemini-1.5-flash:generateContent"
        val response = httpClient.post {
            url(apiUrl)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            parameter("key", API_KEY)
            setBody(Json.encodeToString(requestBody))
        }.body<ResponseModel>()
        return response.candidates[0].content.parts[0].text
    }

    companion object {
        const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models"
        const val API_KEY = "Enter your api key"
    }
}