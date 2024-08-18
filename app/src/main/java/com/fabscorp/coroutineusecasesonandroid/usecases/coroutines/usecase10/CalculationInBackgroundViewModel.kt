package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.fabscorp.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            var result: BigInteger = BigInteger.ONE

            val computationDuration = measureTimeMillis {
                result = calculateFactorialOf(factorialOf)
            }

            var resultString = ""

            val stringConvertionDuration = measureTimeMillis {
                resultString = result.toString()
            }

            uiState.value = UiState.Success (resultString, computationDuration, stringConvertionDuration)
        }
    }

    private fun calculateFactorialOf (number: Int): BigInteger {
        var factorial = BigInteger.ONE

        for (i in 1 .. number) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        return factorial
    }
}