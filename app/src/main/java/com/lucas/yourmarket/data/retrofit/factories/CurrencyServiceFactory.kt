package com.lucas.yourmarket.data.retrofit.factories

import com.lucas.yourmarket.data.retrofit.services.CurrencyService

class CurrencyServiceFactory: ServiceFactory<CurrencyService>() {
    override fun produce(): CurrencyService = retrofit.create(CurrencyService::class.java)
}