package com.lucas.yourmarket.domain.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val Default: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Unconfined: CoroutineDispatcher
}