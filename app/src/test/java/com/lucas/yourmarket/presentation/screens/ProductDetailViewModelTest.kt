package com.lucas.yourmarket.presentation.screens

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.usecases.ProductUseCase
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailRoute
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailViewModel
import com.lucas.yourmarket.presentation.screens.dialog.DialogRoute
import com.lucas.yourmarket.presentation.utils.toCurrency
import com.lucas.yourmarket.test.BaseTest
import com.lucas.yourmarket.test.KoinTestRule
import com.lucas.yourmarket.test.KoinTestRule.Companion.GET_STRING_PLACEHOLDER
import com.lucas.yourmarket.test.TestModuleFactory
import com.nhaarman.mockitokotlin2.*
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString

class ProductDetailViewModelTest : BaseTest() {

    @get:Rule
    val koinRule = KoinTestRule(TestModuleFactory.produce())

    @Test
    fun `Options are loaded when request successfully loaded`() {

        val vm = viewModelFactory()

        val nameMock = vm.name.mock()
        val priceMock = vm.price.mock()
        val hasWarrantyMock = vm.hasWarranty.mock()
        val warrantyMock = vm.warranty.mock()
        val isFreeShippingMock = vm.isFreeShipping.mock()
        val sellerNameMock = vm.sellerName.mock()
        val currencySymbolMock = vm.currencySymbol.mock()
        val sellerLocationMock= vm.sellerLocation.mock()
        val isPlatinumUserMock = vm.isPlatinumUser.mock()
        val reputationMock = vm.reputation.mock()
        val productConditionMock = vm.productCondition.mock()
        val soldQuantityMock = vm.soldQuantity.mock()

        assertThat(nameMock.verifyWithCapture()).isEqualTo(
            "testTitle"
        )
        assertThat(priceMock.verifyWithCapture()).isEqualTo(
            1.0.toCurrency()
        )
        assertThat(hasWarrantyMock.verifyWithCapture()).isEqualTo(
            true
        )
        assertThat(warrantyMock.verifyWithCapture()).isEqualTo(
            "test warranty"
        )
        assertThat(currencySymbolMock.verifyWithCapture()).isEqualTo(
            "$"
        )
        assertThat(isFreeShippingMock.verifyWithCapture()).isEqualTo(
            false
        )
        assertThat(sellerNameMock.verifyWithCapture()).isEqualTo(
            "some nickname"
        )
        assertThat(sellerLocationMock.verifyWithCapture()).isEqualTo(
            "some city name test"
        )
        assertThat(isPlatinumUserMock.verifyWithCapture()).isEqualTo(
            false
        )
        assertThat(reputationMock.verifyWithCapture()).isEqualTo(
            GET_STRING_PLACEHOLDER
        )
        assertThat(productConditionMock.verifyWithCapture()).isEqualTo(
            "new"
        )
        assertThat(soldQuantityMock.verifyWithCapture()).isEqualTo(
            1
        )
    }

    @Test
    fun `When fetching product fails navigator should go to error dialog`() {
        inlineKoinSingle<ProductUseCase> {
            mock {
                onBlocking { execute(any()) } doAnswer {
                    Status.Failure.GeneralFailure(anyString())
                }
            }
        }

        viewModelFactory()

        verify(navigatorMock, times(1)).navigateToRoute(
            DialogRoute.show(
                dialogTitle = GET_STRING_PLACEHOLDER,
                dialogMessage = GET_STRING_PLACEHOLDER,
                onPrimaryClicked = {},
                primaryButtonText = GET_STRING_PLACEHOLDER
            )
        )
    }

    private fun viewModelFactory(): ProductDetailViewModel {
        return ProductDetailViewModel(SavedStateHandle().apply {
            set(ProductDetailRoute.KEY_PRODUCT_ID, "1")
        },
        navigatorMock)
    }
}
