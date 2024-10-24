package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main () {

    val exceptionHandler =
        CoroutineExceptionHandler {coroutineContext, throwable ->
        println("Caught exception $throwable")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.launch {
        println("Coroutine 1 started!!!")
        delay(50)
        println("Coroutine 1 fails!!!")
        throw RuntimeException()
    }

    scope.launch {
        println("Coroutine 2 started!!!")
        delay(500)
        println("Coroutine 2 completed!!!")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException){
            println("Coroutine 2 got cancelled!!")
        }
    }

    Thread.sleep(1000)
    //OUTPUT:
    //Coroutine 2 started!!!
    //Coroutine 1 started!!!
    //Coroutine 1 fails!!!
    //Coroutine 2 got cancelled!!
    //Caught exception java.lang.RuntimeException
    //
    //  NOTE: whit Job() (regular Job) as CoroutineScope parameter, all de scope is cancelled with
    //throw RuntimeException() called in coroutine 1.
}