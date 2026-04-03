package com.proto.mvi.ui.searchvcsuser

import com.proto.mvi.data.model.Repo

sealed interface SearchUiState {
    object Idle : SearchUiState
    object Loading : SearchUiState
    data class Success(val repos: List<Repo>) : SearchUiState
    data class Error(val message: String) : SearchUiState
}

