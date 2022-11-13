package com.lucas.yourmarket.di

import com.lucas.yourmarket.data.retrofit.factories.CurrencyServiceFactory
import com.lucas.yourmarket.data.retrofit.factories.ProductServiceFactory
import com.lucas.yourmarket.data.retrofit.factories.StateServiceFactory
import com.lucas.yourmarket.data.retrofit.factories.UserServiceFactory
import org.koin.dsl.module

val dataModule = module {

    // Service factories
    single { ProductServiceFactory().produce() }
    single { UserServiceFactory().produce() }
    single { CurrencyServiceFactory().produce() }
    single { StateServiceFactory().produce() }

}