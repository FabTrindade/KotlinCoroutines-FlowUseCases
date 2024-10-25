package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

fun main () {

    val exceptionHandler =
        CoroutineExceptionHandler {coroutineContext, throwable ->
        println("Caught exception $throwable")
    }
    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

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
    println("Scope got cancelled: ${!scope.isActive}")
    //OUTPUT:
    //Coroutine 1 started!!!
    //Coroutine 2 started!!!
    //Coroutine 1 fails!!!
    //Caught exception java.lang.RuntimeException
    //Coroutine 2 completed!!!
    //Scope got cancelled: false
    //
    //  NOTE: with Job() (regular Job) as CoroutineScope parameter, all de scope is cancelled with
    //throw RuntimeException() called in coroutine 1.
}