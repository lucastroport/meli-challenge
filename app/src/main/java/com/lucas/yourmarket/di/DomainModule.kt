package com.lucas.yourmarket.di

import com.lucas.yourmarket.domain.interactors.*
import com.lucas.yourmarket.domain.usecases.*
import com.lucas.yourmarket.domain.util.coroutines.DispatcherProvider
import com.lucas.yourmarket.domain.util.coroutines.DispatcherProviderImplementation
import org.koin.dsl.module

val domainModule = module {

    // Use Cases
    single<ClearStorageUseCase> { ClearStorageInteractor() }
    single<ProductUseCase> { ProductInteractor() }
    single<StateUseCase> { StateInteractor() }
    single<UserUseCase> { UserInteractor() }
    single<GetCurrenciesUseCase> { GetCurrenciesInteractor() }

    // Dispatcher Provider
    single<DispatcherProvider> { DispatcherProviderImplementation() }
}
