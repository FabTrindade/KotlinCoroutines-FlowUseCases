package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase6

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.fabscorp.coroutineusecasesonandroid.base.BaseActivity
import com.fabscorp.coroutineusecasesonandroid.base.useCase6Description
import com.fabscorp.coroutineusecasesonandroid.databinding.ActivityRetrynetworkrequestBinding
import com.fabscorp.coroutineusecasesonandroid.utils.fromHtml
import com.fabscorp.coroutineusecasesonandroid.utils.setGone
import com.fabscorp.coroutineusecasesonandroid.utils.setVisible
import com.fabscorp.coroutineusecasesonandroid.utils.toast

class RetryNetworkRequestActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase6Description

    private val binding by lazy { ActivityRetrynetworkrequestBinding.inflate(layoutInflater) }
    private val viewModel: RetryNetworkRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        binding.btnPerformSingleNetworkRequest.setOnClickListener {
            viewModel.performNetworkRequest()
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad()
            }
            is UiState.Success -> {
                onSuccess(uiState)
            }
            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad() = with(binding) {
        progressBar.setVisible()
        textViewResult.text = ""
        btnPerformSingleNetworkRequest.isEnabled = false
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        val readableVersions = uiState.recentVersions.map { "API ${it.apiLevel}: ${it.name}" }
        textViewResult.text = fromHtml(
            "<b>Recent Android Versions</b><br>${readableVersions.joinToString(separator = "<br>")}"
        )
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        toast(uiState.message)
    }
}