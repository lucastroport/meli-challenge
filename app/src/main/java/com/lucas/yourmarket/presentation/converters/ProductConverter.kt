package com.lucas.yourmarket.presentation.converters

import androidx.compose.runtime.Composable
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailState
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailViewModel
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState

@Composable
fun ProductDetailViewModel.toState() = ProductDetailState(
    imagesUrls = mutableListOf(
        "url1","url2"
    ).wrapInState(),
    price = "1.115".wrapInState(),
    currencySymbol = "U\$S".wrapInState(),
    hasWarranty = true.wrapInState(),
    warranty = "Factory Warranty: 12 months".wrapInState(),
    sellerLocation = "Villa Biarritz, Montevideo".wrapInState(),
    sellerName = "SHOP_MERCADOUY".wrapInState(),
    productCondition = "New".wrapInState(),
    isPlatinumUser = true.wrapInState(),
    reputation = "Very Good".wrapInState(),
    imageThumbnailUrl = "thumbnailUrl".wrapInState(),
    isFreeShipping = true.wrapInState(),
    name = "Apple Iphone 13 (128 GB) - Midnight Blue".wrapInState(),
    soldQuantity = 10.wrapInState()
)
