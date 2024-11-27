package com.abhay.aibot.presentation.screens.settingsscreen

import com.abhay.aibot.base.Action

sealed class SettingsAction : Action {
    data class ChangeTheme(val isDarkTheme: Boolean) : SettingsAction()
    data object ClearMessages : SettingsAction()
}