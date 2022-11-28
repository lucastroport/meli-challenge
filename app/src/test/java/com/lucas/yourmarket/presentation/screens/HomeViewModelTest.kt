package com.lucas.yourmarket.presentation.screens

import com.google.common.truth.Truth.assertThat
import com.lucas.yourmarket.presentation.screens.home.HomeViewModel
import com.lucas.yourmarket.test.BaseTest
import com.lucas.yourmarket.test.KoinTestRule
import com.lucas.yourmarket.test.TestModuleFactory
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest : BaseTest() {

    @get:Rule
    val koinRule = KoinTestRule(TestModuleFactory.produce())

    @Test
    fun `When search input empty and search product called don't start loading`(): Unit = runBlocking {
        val vm = viewModelFactory()

        val loadingMock = vm.fullScreenLoading.mock()
        vm.onSearchEnter()
        assertThat(loadingMock.verifyWithCapture(times(1))).isEqualTo(
            false
        )

    }

    @Test
    fun `When empty response callback executed, trigger no results event`() {
        val vm = viewModelFactory()

        val noResultsMock = vm.noResultsEvent.mock()
        vm.onEmptyResponse()
        assertThat(noResultsMock.verifyWithCapture(times(1))).isEqualTo(true)
    }

    private fun viewModelFactory(): HomeViewModel {
        return HomeViewModel(navigatorMock)
    }
}