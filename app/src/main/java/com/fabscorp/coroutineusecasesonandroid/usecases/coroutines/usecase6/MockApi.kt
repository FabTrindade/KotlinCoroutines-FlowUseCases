package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase6

import com.google.gson.Gson
import com.fabscorp.coroutineusecasesonandroid.mock.createMockApi
import com.fabscorp.coroutineusecasesonandroid.mock.mockAndroidVersions
import com.fabscorp.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { "something went wrong on server side" },
            500,
            1000,
            persist = false
        ).mock(
            "http://localhost/recent-android-versions",
            { "something went wrong on server side" },
            500,
            1000,
            persist = false
        ).mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1000
        )
)