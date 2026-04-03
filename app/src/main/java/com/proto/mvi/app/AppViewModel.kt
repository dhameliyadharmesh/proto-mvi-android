package com.proto.mvi.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proto.mvi.di.repos.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = dataStoreRepository
        .isDarkTheme
        .stateIn(
            scope        = viewModelScope,
            started      = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun toggleTheme() {
        viewModelScope.launch {
            dataStoreRepository.toggleTheme()
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.setDarkTheme(enabled)
        }
    }
}