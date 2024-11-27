package com.abhay.aibot.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val candidates: List<Candidate>,
)

@Serializable
data class Candidate(
    val content: Content,
)

@Serializable
data class Content(
    val parts: List<Part>,
    val role: String
)

@Serializable
data class Part(
    val text: String
)