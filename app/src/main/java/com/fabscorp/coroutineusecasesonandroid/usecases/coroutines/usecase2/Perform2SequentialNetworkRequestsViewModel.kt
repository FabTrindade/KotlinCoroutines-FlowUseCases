package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase2

import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi

class Perform2SequentialNetworkRequestsViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {
    }
}