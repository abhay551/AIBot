package com.abhay.aibot.presentation.screens.settingsscreen

import com.abhay.aibot.base.State

data class SettingsState(
    val isClearing: Boolean = false,
    val isDarkTheme: Boolean = false,
) : State
