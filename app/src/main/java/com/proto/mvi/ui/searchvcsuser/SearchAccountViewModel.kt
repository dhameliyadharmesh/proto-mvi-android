
package com.proto.mvi.ui.searchvcsuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proto.mvi.data.repository.GithubRepository
import com.proto.mvi.network.NetworkHelper
import com.proto.mvi.util.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SearchAccountViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun search(username: String) {
        if (username.isBlank()) {
            _uiState.value = SearchUiState.Error("Please enter a username")
            return
        }

        if (!networkHelper.isNetworkConnected()) {
            _uiState.value = SearchUiState.Error("No internet connection")
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            when (val result = repository.getUserRepos(username)) {
                is ResultWrapper.Success -> _uiState.value = SearchUiState.Success(result.value)
                is ResultWrapper.ApiError -> _uiState.value = SearchUiState.Error("API error: ${result.code} ${result.message}")
                is ResultWrapper.NetworkError -> _uiState.value = SearchUiState.Error("Network error: ${result.exception.localizedMessage}")
                is ResultWrapper.UnknownError -> _uiState.value = SearchUiState.Error("Unknown error: ${result.exception.localizedMessage}")
            }
        }
    }
}

