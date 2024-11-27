package com.abhay.aibot.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestModel(val contents: List<RequestContent>)

@Serializable
data class RequestContent(val parts: List<RequestPart>)

@Serializable
data class RequestPart(
    val text: String? = null,
)