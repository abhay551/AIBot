package com.abhay.aibot.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.abhay.aibot.base.BaseViewModel
import com.abhay.aibot.domain.usecase.GetMessagesUseCase
import com.abhay.aibot.presentation.screens.historyscreen.HistoryAction
import com.abhay.aibot.presentation.screens.historyscreen.HistoryEvent
import com.abhay.aibot.presentation.screens.historyscreen.HistoryState
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getMessagesUseCase: GetMessagesUseCase
) : BaseViewModel<HistoryState, HistoryAction, HistoryEvent>(HistoryState()) {

    override fun reduceState(currentState: HistoryState, action: HistoryAction): HistoryState {
        return when (action) {
            is HistoryAction.LoadMessages -> currentState.copy(isLoading = true)
        }
    }

    override fun handleSideEffect(action: HistoryAction, newState: HistoryState) {
        when (action) {
            is HistoryAction.LoadMessages -> loadMessages()
        }
    }

    private fun loadMessages() {
        viewModelScope.launch {
            try {
                val history = getMessagesUseCase()
                setState(HistoryState(messages = history, isLoading = false))
            } catch (_: Exception) {

            }
        }
    }

    fun onLoadMessages() {
        sendAction(HistoryAction.LoadMessages)
    }
}
