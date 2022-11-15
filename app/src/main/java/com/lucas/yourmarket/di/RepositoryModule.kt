package com.lucas.yourmarket.di

import com.lucas.yourmarket.data.repository.implementations.*
import com.lucas.yourmarket.data.repository.interfaces.*
import com.lucas.yourmarket.domain.repository.implementations.CurrencyRepositoryImpl
import com.lucas.yourmarket.domain.repository.implementations.ProductRepositoryImpl
import com.lucas.yourmarket.domain.repository.implementations.StateRepositoryImpl
import com.lucas.yourmarket.domain.repository.implementations.UserRepositoryImpl
import com.lucas.yourmarket.domain.repository.interfaces.CurrencyRepository
import com.lucas.yourmarket.domain.repository.interfaces.ProductRepository
import com.lucas.yourmarket.domain.repository.interfaces.StateRepository
import com.lucas.yourmarket.domain.repository.interfaces.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Repositories
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory<StateRepository> { StateRepositoryImpl(get(), get()) }
    factory<CurrencyRepository> { CurrencyRepositoryImpl(get(), get()) }
}