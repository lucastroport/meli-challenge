package com.lucas.yourmarket.test

import com.lucas.yourmarket.domain.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : DispatcherProvider {
    override val Default: CoroutineDispatcher = Dispatchers.Unconfined
    override val IO: CoroutineDispatcher = Dispatchers.Unconfined
    override val Main: CoroutineDispatcher = Dispatchers.Unconfined
    override val Unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}
