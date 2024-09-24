package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    var coroutineJob= scope.launch {
        println("Starting coroutine...")
        delay(1000)
    }

    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
    //OUTPUT: Is coroutineJob a child of scopeJob? => true
}