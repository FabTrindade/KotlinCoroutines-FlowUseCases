package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.fabscorp.coroutineusecasesonandroid.mock.VersionFeatures

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val versionFeatures: VersionFeatures
    ) : UiState()

    data class Error(val message: String) : UiState()
}