package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase14

import com.fabscorp.coroutineusecasesonandroid.mock.AndroidVersion
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.CoroutineScope

class AndroidVersionRepository(
    private var database: AndroidVersionDao,
    private val scope: CoroutineScope,
    private val api: MockApi = mockApi()
) {

    suspend fun getLocalAndroidVersions(): List<AndroidVersion> {
        return database.getAndroidVersions().mapToUiModelList()
    }

    suspend fun loadAndStoreRemoteAndroidVersions(): List<AndroidVersion> {
        return emptyList()
    }

    fun clearDatabase() {

    }
}