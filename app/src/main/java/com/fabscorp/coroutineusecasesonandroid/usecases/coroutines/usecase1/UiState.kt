package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase1

import com.fabscorp.coroutineusecasesonandroid.mock.AndroidVersion

sealed class UiState {
    object Loading : UiState()
    data class Success(val recentVersions: List<AndroidVersion>) : UiState()
    data class Error(val message: String) : UiState()
}