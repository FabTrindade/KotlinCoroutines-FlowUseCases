package com.fabscorp.coroutineusecasesonandroid.playground.utils.structuredconcorrency

import android.provider.Settings.Global
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    println("Job of GlobalScope: ${GlobalScope.coroutineContext[Job]}")

    //NOTE: GlobalScope doesn't has a Job associated and its ude should avoided! GlobalScope doesn't
    //create a Coroutine hierarchy of parents an children
    //
    //OUTPUT:
    //Job of GlobalScope: null


    GlobalScope.launch {

    }
}