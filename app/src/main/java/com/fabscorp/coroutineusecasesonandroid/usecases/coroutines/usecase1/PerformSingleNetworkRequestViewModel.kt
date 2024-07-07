package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase1

import androidx.lifecycle.viewModelScope
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch

class PerformSingleNetworkRequestViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performSingleNetworkRequest() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val recentAndroidVersions = mockApi.getRecentAndroidVersions()
            uiState.value = UiState.Success(recentAndroidVersions)
        }
    }
}