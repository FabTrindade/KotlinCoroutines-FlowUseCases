package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {

    }
}