package com.lucas.yourmarket.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.mockito.verification.VerificationMode

open class BaseTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule(TestDispatcherProvider().Unconfined)

    /* Used to declare a Koin single within a module and load immediately */
    inline fun <reified T> inlineKoinSingle(
        crossinline block: Scope.() -> T
    ) = loadKoinModules(module { single { block() } })

    // Used to bracket mock block so generic types can be fully inferred.
    protected fun <T> LiveData<T>.mock(): Observer<T> =
        mock { observeForever(mock) }

    // Pulls last value from mocked observable
    protected inline fun <reified T : Any> Observer<T>.verifyWithCapture(
        mode: VerificationMode = times(1)
    ): T {
        val captor = argumentCaptor<T>()
        verify(this, mode).onChanged(captor.capture())
        return captor.lastValue
    }

    // Common mocks
    val navigatorMock = mock<RouteNavigator> {}
}