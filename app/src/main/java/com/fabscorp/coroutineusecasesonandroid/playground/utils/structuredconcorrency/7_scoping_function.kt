package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val scope = CoroutineScope(Job())

    scope.launch {
        launch {
            println("Starting Task 1!")
            delay(100)
            println("Task 1 completed!")
        }
        launch {
            println("Starting Task 2!")
            delay(200)
            println("Task 2 completed!")
        }
        launch {
            println("Starting Task 3!")
            delay(300)
            println("Task 3 completed!")
        }
    }
    Thread.sleep(1000)

    //OUTPUT:
    //Starting Task 1!
    //Starting Task 2!
    //Starting Task 3!
    //Task 1 completed!
    //Task 2 completed!
    //Task 3 completed!
    //
    //OBS: all tasks run in parallel!
}