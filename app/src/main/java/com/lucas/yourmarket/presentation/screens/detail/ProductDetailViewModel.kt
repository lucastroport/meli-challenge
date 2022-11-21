package com.lucas.yourmarket.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import com.lucas.yourmarket.domain.usecases.ProductUseCase
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import org.koin.core.component.inject

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator
) : BaseViewModel(),
    RouteNavigator by routeNavigator
{
    private val productId = ProductDetailRoute.getIndexFrom(savedStateHandle)

    // DI
    private val productUseCase: ProductUseCase by inject()

    


}