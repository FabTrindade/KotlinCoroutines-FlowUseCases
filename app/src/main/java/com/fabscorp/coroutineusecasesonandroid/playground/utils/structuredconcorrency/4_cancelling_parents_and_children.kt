package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit>{
    val scope = CoroutineScope(Dispatchers.Default)

    scope.coroutineContext[Job]!!.invokeOnCompletion { throwable ->
        if (throwable is CancellationException){
            println("Parent Job was cancelled!")
        }
    }

    val childCoroutine1Job = scope.launch {
        delay(1000)
        println("Coroutine 1 completed!")
    }

    childCoroutine1Job.invokeOnCompletion { throwable ->
        if (throwable is CancellationException){
            println("Coroutine 1 was cancelled!")
        }
    }
    scope.launch {
        delay(1000)
        println("Coroutine 2 completed!")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException){
            println("Coroutine 2 was cancelled!")
        }
    }

    delay(200)
    childCoroutine1Job.cancelAndJoin()
    delay(2000)

    // Cancelling a child coroutine doesn't cancel its parent
    //OUTPUT:
    //Coroutine 1 was cancelled!
    //Coroutine 2 completed!
    //Process finished with exit code 0

}