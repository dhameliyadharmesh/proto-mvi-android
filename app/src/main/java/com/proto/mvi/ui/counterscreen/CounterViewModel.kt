package com.proto.mvi.ui.counterscreen

// CounterViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    fun processIntent(intent: CounterIntent) {
        when (intent) {
            CounterIntent.Increment -> {
                _state.update { it.copy(count = it.count + 1) }
            }

            CounterIntent.Decrement -> {
                _state.update { it.copy(count = it.count - 1) }
            }

            CounterIntent.SimulateLoading -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    delay(1500) // Simulate network delay
                    _state.update {
                        it.copy(isLoading = false, count = it.count + 5)
                    }
                }
            }
        }
    }

    // Optional: Handle errors
    private fun handleError(message: String) {
        _state.update { it.copy(error = message, isLoading = false) }
    }
}