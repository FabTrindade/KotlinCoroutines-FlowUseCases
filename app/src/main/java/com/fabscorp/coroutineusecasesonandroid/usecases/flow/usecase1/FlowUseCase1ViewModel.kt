package com.fabscorp.coroutineusecasesonandroid.usecases.flow.usecase1

import androidx.lifecycle.LiveData
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel

class FlowUseCase1ViewModel(
    stockPriceDataSource: StockPriceDataSource
) : BaseViewModel<UiState>() {

    val currentStockPriceAsLiveData: LiveData<UiState> = TODO()

}