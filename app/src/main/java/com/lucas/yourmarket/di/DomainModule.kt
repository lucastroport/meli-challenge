package com.lucas.yourmarket.di

import com.lucas.yourmarket.domain.interactors.ClearStorageInteractor
import com.lucas.yourmarket.domain.interactors.ProductInteractor
import com.lucas.yourmarket.domain.interactors.StateInteractor
import com.lucas.yourmarket.domain.interactors.UserInteractor
import org.koin.dsl.module

val domainModule = module {

    // Use Cases
    single { ClearStorageInteractor() }
    single { ProductInteractor() }
    single { StateInteractor() }
    single { UserInteractor() }
}
