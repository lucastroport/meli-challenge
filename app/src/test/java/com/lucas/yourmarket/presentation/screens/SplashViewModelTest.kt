package com.lucas.yourmarket.presentation.screens

import com.lucas.yourmarket.domain.model.response.asSuccess
import com.lucas.yourmarket.domain.usecases.BaseUseCase
import com.lucas.yourmarket.domain.usecases.GetCurrenciesUseCase
import com.lucas.yourmarket.presentation.screens.dialog.DialogRoute
import com.lucas.yourmarket.presentation.screens.home.HomeRoute
import com.lucas.yourmarket.presentation.screens.splash.SplashRoute
import com.lucas.yourmarket.presentation.screens.splash.SplashViewModel
import com.lucas.yourmarket.test.BaseTest
import com.lucas.yourmarket.test.KoinTestRule
import com.lucas.yourmarket.test.TestModuleFactory
import com.nhaarman.mockitokotlin2.*
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest : BaseTest() {

    @get:Rule
    val koinRule = KoinTestRule(TestModuleFactory.produce())

    @Test
    fun `Fetch currencies are called on init`(): Unit = runBlocking{

       viewModelFactory()

        verify(getCurrenciesUseCaseMock,
            times(1)
        ).execute(anyOrNull())
    }

    @Test
    fun `Go to home screen on Init`() {
        viewModelFactory()

        verify(navigatorMock, times(1)).popToRoute(
            destination = HomeRoute.route,
            popUpTo = SplashRoute.route,
            inclusive = true
        )
    }

    // Mocks
    private val getCurrenciesUseCaseMock = mock<GetCurrenciesUseCase> {
        onBlocking { execute(any()) } doAnswer { BaseUseCase.Response().asSuccess() }
    }

    private fun viewModelFactory(): SplashViewModel {
        inlineKoinSingle { getCurrenciesUseCaseMock }
        return SplashViewModel(navigatorMock)
    }
}