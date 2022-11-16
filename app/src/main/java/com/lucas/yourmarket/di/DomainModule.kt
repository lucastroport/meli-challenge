package com.lucas.yourmarket.di

import com.lucas.yourmarket.domain.interactors.*
import org.koin.dsl.module

val domainModule = module {

    // Use Cases
    single { ClearStorageInteractor() }
    single { ProductInteractor() }
    single { StateInteractor() }
    single { UserInteractor() }
    single { GetCurrenciesInteractor() }
}
