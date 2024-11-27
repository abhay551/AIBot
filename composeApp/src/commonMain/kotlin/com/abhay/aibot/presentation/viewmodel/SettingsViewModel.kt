package com.abhay.aibot.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.abhay.aibot.base.BaseViewModel
import com.abhay.aibot.domain.usecase.ClearMessageUseCase
import com.abhay.aibot.presentation.screens.settingsscreen.SettingsAction
import com.abhay.aibot.presentation.screens.settingsscreen.SettingsState
import com.abhay.aibot.presentation.screens.settingsscreen.SettingsViewEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val clearMessagesUseCase: ClearMessageUseCase
) : BaseViewModel<SettingsState, SettingsAction, SettingsViewEvent>(SettingsState()) {

    override fun reduceState(currentState: SettingsState, action: SettingsAction): SettingsState {
        return when (action) {
            is SettingsAction.ClearMessages -> currentState.copy(isClearing = true)
            is SettingsAction.ChangeTheme -> currentState.copy(isDarkTheme = action.isDarkTheme)
        }
    }

    override fun handleSideEffect(action: SettingsAction, newState: SettingsState) {
        when (action) {
            is SettingsAction.ClearMessages -> clearMessages()
            else -> Unit
        }
    }

    private fun clearMessages() {
        viewModelScope.launch {
            try {
                clearMessagesUseCase.invoke()
                delay(500)
                sendEvent(SettingsViewEvent.MessagesCleared)
                setState(SettingsState(isClearing = false))
            } catch (e: Exception) {
                setState(SettingsState(isClearing = false))
            }
        }
    }
}