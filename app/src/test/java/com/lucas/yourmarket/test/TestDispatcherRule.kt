package com.lucas.yourmarket.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestDispatcherRule(private val dispatcher: CoroutineDispatcher) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) = Dispatchers.setMain(dispatcher)
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) = Dispatchers.resetMain()
}
