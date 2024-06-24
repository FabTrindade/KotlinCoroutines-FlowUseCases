package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase2.rx

import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel

class SequentialNetworkRequestsRxViewModel(
    private val mockApi: RxMockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun perform2SequentialNetworkRequest() {

    }
}