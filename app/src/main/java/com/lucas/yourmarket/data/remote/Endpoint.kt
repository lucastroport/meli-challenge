package com.lucas.yourmarket.data.remote

object Endpoint {

    // User
    const val ENDPOINT_GET_USER_BY_ID = "users/{userId}"

    // Product
    const val ENDPOINT_SEARCH_PRODUCT = "sites/{siteId}/search"
    const val ENDPOINT_GET_PRODUCT = "items/{itemId}"

    // Currency
    const val ENDPOINT_GET_CURRENCIES = "currencies"

    // State
    const val ENDPOINT_GET_STATE = "classified_locations/states/{stateId}"
}