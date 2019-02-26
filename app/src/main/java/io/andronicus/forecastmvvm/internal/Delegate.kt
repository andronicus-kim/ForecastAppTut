package io.andronicus.forecastmvvm.internal

import kotlinx.coroutines.*

/**
 * Created by Andronicus on 2/26/2019.
 */

fun <T> lazyDeferred(block : suspend CoroutineScope.() -> T) : Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}