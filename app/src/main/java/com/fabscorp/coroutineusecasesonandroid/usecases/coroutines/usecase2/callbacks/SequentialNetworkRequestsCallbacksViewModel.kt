package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase2.callbacks

import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import com.fabscorp.coroutineusecasesonandroid.mock.AndroidVersion
import com.fabscorp.coroutineusecasesonandroid.mock.VersionFeatures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SequentialNetworkRequestsCallbacksViewModel(
    private val mockApi: CallbackMockApi = mockApi()
) : BaseViewModel<UiState>() {

    private var getAndroidVersion: Call <List<AndroidVersion>>? = null
    private var getAndroidFeatures: Call <VersionFeatures>? = null

    fun perform2SequentialNetworkRequest() {
        uiState.value = UiState.Loading

        getAndroidVersion = mockApi.getRecentAndroidVersions()

        getAndroidVersion!!.enqueue(object: Callback<List<AndroidVersion>> {
            override fun onResponse(
                call: Call<List<AndroidVersion>>,
                response: Response<List<AndroidVersion>>
            ) {
                if (response.isSuccessful) {
                    val mostRecentVersion = response.body()!!.last()
                    getAndroidFeatures = mockApi.getAndroidVersionFeatures(mostRecentVersion.apiLevel)

                    getAndroidFeatures!!.enqueue(object: Callback<VersionFeatures>{
                        override fun onResponse(
                            call: Call<VersionFeatures>,
                            response: Response<VersionFeatures>
                        ) {
                            if (response.isSuccessful) {
                              uiState.value = UiState.Success(response.body()!!)
                            } else {
                                uiState.value = UiState.Error ("Network request failed!")
                            }

                        }

                        override fun onFailure(call: Call<VersionFeatures>, t: Throwable) {
                            uiState.value = UiState.Error ("Network request failed!")
                        }

                    })
                } else {
                    uiState.value = UiState.Error ("Network request failed!")
                }

            }

            override fun onFailure(call: Call<List<AndroidVersion>>, t: Throwable) {
                uiState.value = UiState.Error ("Something unexpected happening!")
            }
        })
    }

    override fun onCleared() {
        super.onCleared()

        getAndroidVersion?.cancel()
        getAndroidFeatures?.cancel()
    }
}