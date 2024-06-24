package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase2

import com.google.gson.Gson
import com.fabscorp.coroutineusecasesonandroid.mock.createMockApi
import com.fabscorp.coroutineusecasesonandroid.mock.mockAndroidVersions
import com.fabscorp.coroutineusecasesonandroid.mock.mockVersionFeaturesAndroid10
import com.fabscorp.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1500
        )
        .mock(
            "http://localhost/android-version-features/29",
            { Gson().toJson(mockVersionFeaturesAndroid10) },
            200,
            1500
        )
)