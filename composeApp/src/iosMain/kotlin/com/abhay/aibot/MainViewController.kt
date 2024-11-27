package com.abhay.aibot

import androidx.compose.ui.window.ComposeUIViewController
import com.abhay.aibot.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
    startKoin {
        modules(appModule)
    }
}) { App() }