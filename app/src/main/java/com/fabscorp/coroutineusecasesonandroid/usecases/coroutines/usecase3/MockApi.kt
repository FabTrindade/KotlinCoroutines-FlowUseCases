package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase3

import com.google.gson.Gson
import com.fabscorp.coroutineusecasesonandroid.mock.createMockApi
import com.fabscorp.coroutineusecasesonandroid.mock.mockVersionFeaturesAndroid10
import com.fabscorp.coroutineusecasesonandroid.mock.mockVersionFeaturesOreo
import com.fabscorp.coroutineusecasesonandroid.mock.mockVersionFeaturesPie
import com.fabscorp.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/android-version-features/27",
            { Gson().toJson(mockVersionFeaturesOreo) },
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/28",
            { Gson().toJson(mockVersionFeaturesPie) },
            200,
            1000
        )
        .mock(
            "http://localhost/android-version-features/29",
            { Gson().toJson(mockVersionFeaturesAndroid10) },
            200,
            1000
        )
)