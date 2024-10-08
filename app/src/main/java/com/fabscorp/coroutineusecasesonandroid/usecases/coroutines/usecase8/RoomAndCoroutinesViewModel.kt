package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase8

import androidx.lifecycle.viewModelScope
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch

class RoomAndCoroutinesViewModel(
    private val api: MockApi,
    private val database: AndroidVersionDao
) : BaseViewModel<UiState>() {

    fun loadData() {
        uiState.value = UiState.Loading.LoadFromDb

        viewModelScope.launch {
            val localVersions = database.getAndroidVersions()

            if (localVersions.isEmpty()) {
                uiState.value = UiState.Error (DataSource.DATABASE, "Database is empty!")
            } else {
                uiState.value = UiState.Success (DataSource.DATABASE, localVersions.mapToUiModelList())
            }

            uiState.value = UiState.Loading.LoadFromNetwork

            try {
                val recentVersions = api.getRecentAndroidVersions()

                for (version in recentVersions) {
                    database.insert(version.mapToEntity())
                }
                uiState.value  = UiState.Success (DataSource.NETWORK, recentVersions )
            } catch (e: Exception) {
                uiState.value = UiState.Error (DataSource.NETWORK, "Something went wrong!")
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            database.clear()
        }
    }
}

enum class DataSource(val dataSourceName: String) {
    DATABASE("Database"),
    NETWORK("Network")
}