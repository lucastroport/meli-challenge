package com.lucas.yourmarket.di

import com.lucas.yourmarket.data.remote.datastore.implementations.*
import com.lucas.yourmarket.data.remote.datastore.interfaces.*
import com.lucas.yourmarket.data.remote.factories.*
import org.koin.dsl.module

val networkModule = module {

    // Service factories
    single { ProductServiceFactory().produce() }
    single { UserServiceFactory().produce() }
    single { CurrencyServiceFactory().produce() }
    single { StateServiceFactory().produce() }

    // Remote datastore
    factory<ProductRemoteDatastore> { ProductRemoteDatastoreImpl(get()) }
    factory<CurrencyRemoteDatastore> { CurrencyRemoteDatastoreImpl(get()) }
    factory<StateRemoteDatastore> { StateRemoteDatastoreImpl(get()) }
    factory<UserRemoteDatastore> { UserRemoteDatastoreImpl(get()) }
}
