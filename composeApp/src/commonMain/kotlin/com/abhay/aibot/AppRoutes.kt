package com.abhay.aibot

sealed class AppRoutes(val routes: String) {
    data object ChatScreen : AppRoutes(routes = "chat")
    data object HistoryScreen : AppRoutes(routes = "history")
    data object SettingsScreen : AppRoutes(routes = "settings")
}