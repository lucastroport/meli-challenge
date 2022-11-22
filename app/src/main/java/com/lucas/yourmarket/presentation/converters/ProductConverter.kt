package com.lucas.yourmarket.presentation.converters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailState
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailViewModel

@Composable
fun ProductDetailViewModel.toState() = ProductDetailState(
    loading = fullScreenLoading.observeAsState(),
    imagesUrls = imagesUrls.observeAsState(),
    price = price.observeAsState(),
    currencySymbol = currencySymbol.observeAsState(),
    hasWarranty = hasWarranty.observeAsState(),
    warranty = warranty.observeAsState(),
    sellerLocation = sellerLocation.observeAsState(),
    sellerName = sellerName.observeAsState(),
    productCondition = productCondition.observeAsState(),
    isPlatinumUser = isPlatinumUser.observeAsState(),
    reputation = reputation.observeAsState(),
    isFreeShipping = isFreeShipping.observeAsState(),
    name = name.observeAsState(),
    soldQuantity = soldQuantity.observeAsState()
)
