package com.fabscorp.coroutineusecasesonandroid.usecases.flow.usecase3

import com.fabscorp.coroutineusecasesonandroid.usecases.flow.mock.Stock

sealed class UiState {
    object Loading : UiState()
    data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}