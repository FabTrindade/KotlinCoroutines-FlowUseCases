package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    var childCoroutineJob: Job? = null
    val coroutineJob= scope.launch {

        childCoroutineJob = launch {
            println("Starting child coroutine...")
            delay(1000)
        }

        println("Starting coroutine...")
        delay(1000)
    }

    Thread.sleep(1000)

    println("Is childCoroutineJob a child of coroutineJob? => ${coroutineJob.children.contains(childCoroutineJob)}")
    //OUTPUT: Is childCoroutineJob a child of coroutineJob? => true

    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
    //OUTPUT: Is coroutineJob a child of scopeJob? => true
}