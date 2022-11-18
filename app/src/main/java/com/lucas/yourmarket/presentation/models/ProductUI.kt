package com.lucas.yourmarket.presentation.models

data class ProductUI(
    val id: Long,
    val name: String? = null,
    val price: String? = null,
    val hasWarranty: Boolean? = false,
    val warranty: String? = null,
    val imageThumbnailUrl: String? = null,
    val imagesUrl: List<String> = emptyList(),
    val isFreeShipping: Boolean? = null,
    val sellerName: String? = null,
    val currencySymbol: String? = null,
    val sellerLocation: String? = null,
    val isPlatinumUser: Boolean? = null,
    val reputation: String? = null,
    val productCondition: String? = null,
    val soldQuantity: String? = null
)