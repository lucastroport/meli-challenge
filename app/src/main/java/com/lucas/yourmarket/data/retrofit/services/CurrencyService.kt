package com.lucas.yourmarket.data.retrofit.services

import com.lucas.yourmarket.data.model.Currency
import com.lucas.yourmarket.data.retrofit.Endpoint.ENDPOINT_GET_CURRENCIES
import retrofit2.http.GET

interface CurrencyService {
    @GET(ENDPOINT_GET_CURRENCIES)
    suspend fun fetchCurrencies(): List<Currency>
}
