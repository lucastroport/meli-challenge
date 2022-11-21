package com.lucas.yourmarket.di

import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.navigation.RouteNavigatorImplementation
import com.lucas.yourmarket.presentation.screens.home.HomeViewModel
import com.lucas.yourmarket.presentation.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Route Navigator
    factory<RouteNavigator> { RouteNavigatorImplementation() }

    // ViewModel
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get()) }

}