package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase7

import com.fabscorp.coroutineusecasesonandroid.mock.VersionFeatures

sealed class UiState {
    object Loading : UiState()
    data class Success(val versionFeatures: List<VersionFeatures>) : UiState()
    data class Error(val message: String) : UiState()
}