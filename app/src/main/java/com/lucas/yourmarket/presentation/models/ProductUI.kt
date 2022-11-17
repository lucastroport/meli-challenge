package com.lucas.yourmarket.presentation.models

data class ProductUI(
    val name: String,
    val price: String,
    val hasWarranty: Boolean,
    val warranty: String,
    val imageThumbnailUrl: String,
    val imagesUrl: List<String>,
    val isFreeShipping: Boolean,
    val sellerName: String,
    val currencySymbol: String,
    val sellerLocation: String,
    val isPlatinumUser: Boolean,
    val reputation: String,
    val productCondition: String
)