package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    val scope = CoroutineScope(Dispatchers.Default)

    val parentCoroutineJob = scope.launch {
        launch {
            delay(1000)
            println("Child Coroutine 1 completed!")
        }

        launch {
            delay(1000)
            println("Child Coroutine 2 completed!")
        }
    }

    //Suspends the coroutine started with runBlocking until this job is complete.
    parentCoroutineJob.join()

    println("Parent coroutine is completed!")
    //OUTPUT:
    //Child Coroutine 2 completed!
    //Child Coroutine 1 completed!
    //Parent coroutine is completed!
}

