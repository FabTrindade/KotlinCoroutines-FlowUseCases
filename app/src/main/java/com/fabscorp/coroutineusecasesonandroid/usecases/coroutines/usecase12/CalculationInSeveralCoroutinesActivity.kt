package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase12

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.fabscorp.coroutineusecasesonandroid.R
import com.fabscorp.coroutineusecasesonandroid.base.BaseActivity
import com.fabscorp.coroutineusecasesonandroid.base.useCase12Description
import com.fabscorp.coroutineusecasesonandroid.databinding.ActivityCalculationinmultiplebackgroundthreadsBinding
import com.fabscorp.coroutineusecasesonandroid.utils.hideKeyboard
import com.fabscorp.coroutineusecasesonandroid.utils.setGone
import com.fabscorp.coroutineusecasesonandroid.utils.setVisible
import com.fabscorp.coroutineusecasesonandroid.utils.toast

class CalculationInSeveralCoroutinesActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase12Description

    private val binding by lazy {
        ActivityCalculationinmultiplebackgroundthreadsBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: CalculationInSeveralCoroutinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState()
            .observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })
        binding.btnCalculate.setOnClickListener {
            val factorialOf = binding.editTextFactorialOf.text.toString().toIntOrNull()
            val numberOfThreads = binding.editTextNumberOfThreads.text.toString().toIntOrNull()
            if (factorialOf != null && numberOfThreads != null) {
                viewModel.performCalculation(
                    factorialOf,
                    numberOfThreads
                )
            }
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
        textViewDuration.text = ""
        textViewStringConversionDuration.text = ""
        btnCalculate.isEnabled = false
        textViewResult.hideKeyboard()
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        textViewDuration.text =
            getString(R.string.duration_calculation, uiState.computationDuration)
        textViewStringConversionDuration.text =
            getString(R.string.duration_stringconversion, uiState.stringConversionDuration)
        progressBar.setGone()
        btnCalculate.isEnabled = true
        textViewResult.text = if (uiState.result.length <= 150) {
            uiState.result
        } else {
            "${uiState.result.substring(0, 147)}..."
        }
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnCalculate.isEnabled = true
        toast(uiState.message)
    }
}