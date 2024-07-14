package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase5

import androidx.lifecycle.viewModelScope
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import timber.log.Timber

class NetworkRequestWithTimeoutViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequest(timeout: Long) {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val recentAndroidVersions = withTimeout(timeout) {
                    api.getRecentAndroidVersions()
                }
                uiState.value = UiState.Success(recentAndroidVersions)

            } catch (timeOutException: TimeoutCancellationException) {
                uiState.value = UiState.Error("Network request timed out!")
            }
            catch (exception: Exception) {
                Timber.e(exception)
                uiState.value = UiState.Error("Network request failed!")
            }
        }
    }

}