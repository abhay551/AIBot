package com.abhay.aibot.di

import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun provideHttpClient(): HttpClient

expect fun generateUUID(): String
expect fun currentTimeMillis(): Long
