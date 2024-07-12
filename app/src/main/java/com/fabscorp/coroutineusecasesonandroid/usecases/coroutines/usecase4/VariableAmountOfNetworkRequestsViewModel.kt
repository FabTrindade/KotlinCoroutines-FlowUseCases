package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase4

import androidx.lifecycle.viewModelScope
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class VariableAmountOfNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val androidVersions = mockApi.getRecentAndroidVersions()

                val androidFeatures = androidVersions.map { androidVersion ->
                    mockApi.getAndroidVersionFeatures(androidVersion.apiLevel)
                }
                uiState.value = UiState.Success(androidFeatures)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network request failed!")
            }
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val androidVersions = mockApi.getRecentAndroidVersions()

                val androidFeatures = androidVersions.map { androidVersion ->
                    async {// viewModelScope isn't needed because launch scope already invoked
                        mockApi.getAndroidVersionFeatures(androidVersion.apiLevel)
                    }
                }.awaitAll()

                uiState.value = UiState.Success(androidFeatures)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network request failed!")
            }
        }

    }
}