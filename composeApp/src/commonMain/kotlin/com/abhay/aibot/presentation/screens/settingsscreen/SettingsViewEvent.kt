package com.abhay.aibot.presentation.screens.settingsscreen

import com.abhay.aibot.base.Event

sealed class SettingsViewEvent : Event {
    data object MessagesCleared : SettingsViewEvent()
}