package com.abhay.aibot.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel class to manage MVI pattern: State, Action, Event
 */
abstract class BaseViewModel<S : State, A : Action, E : Event>(
    initialState: S
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _action = Channel<A>(Channel.BUFFERED)
    val action = _action.receiveAsFlow()

    private val _uiEvent = Channel<E>(Channel.BUFFERED)
    val uiEvent: Flow<E> = _uiEvent.receiveAsFlow()

    private val scope = viewModelScope

    init {
        processActions()
    }

    /**
     * Abstract method to define how the state should change based on the action
     */
    protected abstract fun reduceState(currentState: S, action: A): S

    /**
     * Abstract method to handle side-effects such as API calls, navigation, or showing toasts
     */
    protected abstract fun handleSideEffect(action: A, newState: S)

    /**
     * Send an action to trigger state changes
     */
    public fun sendAction(newAction: A) {
        scope.launch { _action.send(newAction) }
    }

    /**
     * Send a UI event like a Toast, Snackbar, or navigation action
     */
    public fun sendEvent(event: E) {
        scope.launch { _uiEvent.send(event) }
    }

    /**
     * Send a new state to the UI
     */
    public fun setState(newState: S) {
        scope.launch {
            _uiState.value = newState
        }
    }

    private fun processActions() {
        action.onEach {
            val newState = reduceState(uiState.value, it)
            setState(newState)
            handleSideEffect(it, newState)
        }.launchIn(scope)
    }
}