package com.fabscorp.coroutineusecasesonandroid.usecases.flow.usecase4

import android.content.Context
import com.google.gson.Gson
import com.fabscorp.coroutineusecasesonandroid.usecases.flow.mock.createFlowMockApi
import com.fabscorp.coroutineusecasesonandroid.usecases.flow.mock.fakeCurrentStockPrices
import com.fabscorp.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApi(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = { Gson().toJson(fakeCurrentStockPrices(context)) },
                status = 200,
                delayInMs = 1500,
            )
    )