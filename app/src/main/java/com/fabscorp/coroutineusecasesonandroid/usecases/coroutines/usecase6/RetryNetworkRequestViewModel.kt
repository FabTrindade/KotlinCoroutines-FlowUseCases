package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase6

import androidx.lifecycle.viewModelScope
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class RetryNetworkRequestViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            val numberOfRetries = 2
            try {
                retry(numberOfRetries) {
                    loadRecentVersions()
                }
            } catch (exception: Exception) {
                Timber.e(exception)
                uiState.value = UiState.Error("Network request failed!")
            }
        }
    }

    private suspend fun  <T> retry (
        numberOfRetries: Int,
        initialDelayMillis: Long = 100,
        maxDelayMillis: Long = 1000,
        factor: Double = 2.0,
        block: suspend ()-> T
    ): T  {

        var currentDelay = initialDelayMillis

        repeat(numberOfRetries) {
            try {
                return block()
            } catch (exception: Exception) {
                Timber.e (exception)
            }
            delay(currentDelay)
            currentDelay = (currentDelay*factor).toLong().coerceAtMost(maxDelayMillis)
        }
        return block()
    }
    private suspend fun loadRecentVersions() {
        val recentAndroidVersions = api.getRecentAndroidVersions()
        uiState.value = UiState.Success(recentAndroidVersions)
    }
}