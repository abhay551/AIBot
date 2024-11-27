package com.abhay.aibot.presentation.screens.historyscreen

import com.abhay.aibot.base.Action

sealed class HistoryAction : Action {
    data object LoadMessages : HistoryAction()
}