package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    val passedJob = Job()

    val coroutineJob= scope.launch (passedJob) {
        println("Starting coroutine...")
        delay(1000)
    }

    println("passedJob and coroutineJob are referenced to the same job object? => ${passedJob === coroutineJob}")
    //OUTPUT: passedJob and coroutineJob are referenced to the same job object? => false

    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
    //OUTPUT: Is coroutineJob a child of scopeJob? => false
    //Notice this big change!!!!
}