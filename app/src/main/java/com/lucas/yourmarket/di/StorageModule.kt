package com.lucas.yourmarket.di

import com.lucas.yourmarket.data.storage.datastore.implementations.*
import com.lucas.yourmarket.data.storage.datastore.interfaces.*
import com.lucas.yourmarket.data.storage.db.YourMarketDb
import com.lucas.yourmarket.data.storage.db.provideDatabase
import org.koin.dsl.module

val storageModule = module {

    // Database
    single { provideDatabase(get()) }

    // DAOs
    factory { get<YourMarketDb>().userDao() }
    factory { get<YourMarketDb>().productDao() }
    factory { get<YourMarketDb>().currencyDao() }
    factory { get<YourMarketDb>().stateDao() }
    factory { get<YourMarketDb>().youMarketRemoteKeyDao() }

    // Datastore
    factory<ProductLocalDatastore> { ProductLocalDatastoreImpl(get()) }
    factory<RemoteKeyDatastore> { RemoteKeyDatastoreImpl(get()) }
    factory<UserLocalDatastore> { UserLocalDatastoreImpl(get()) }
    factory<StateLocalDatastore> { StateLocalDatastoreImpl(get()) }
    factory<CurrencyLocalDatastore> { CurrencyLocalDatastoreImpl(get()) }
}
