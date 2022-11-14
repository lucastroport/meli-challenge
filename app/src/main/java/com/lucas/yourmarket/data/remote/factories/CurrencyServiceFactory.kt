package com.lucas.yourmarket.data.remote.factories

import com.lucas.yourmarket.data.remote.services.CurrencyService

class CurrencyServiceFactory: ServiceFactory<CurrencyService>() {
    override fun produce(): CurrencyService = retrofit.create(CurrencyService::class.java)
}