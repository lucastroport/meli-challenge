package com.lucas.yourmarket.di

import com.lucas.yourmarket.data.repository.implementations.*
import com.lucas.yourmarket.data.repository.interfaces.*
import org.koin.dsl.module

val repositoryModule = module {

    // Repositories
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory<StateRepository> { StateRepositoryImpl(get(), get()) }
    factory<CurrencyRepository> { CurrencyRepositoryImpl(get(), get()) }
}