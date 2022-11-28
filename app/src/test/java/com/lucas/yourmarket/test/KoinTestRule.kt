package com.lucas.yourmarket.test

import android.content.Context
import com.nhaarman.mockitokotlin2.whenever
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.mockito.Mockito.*

class KoinTestRule(
    private val module: Module
) : TestWatcher() {
    lateinit var context: Context
    companion object {
        const val GET_STRING_PLACEHOLDER = "ANY_STRING"
    }
    override fun starting(description: Description) {
        context = mock(Context::class.java)
        whenever(context.getString(anyInt())).thenReturn(GET_STRING_PLACEHOLDER)
        startKoin {
            androidContext(context)
            modules(module)
        }
    }
    override fun finished(description: Description) = stopKoin()
}