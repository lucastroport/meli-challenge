package com.lucas.yourmarket.presentation.screens.splash

import com.lucas.yourmarket.domain.usecases.GetCurrenciesUseCase
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import com.lucas.yourmarket.presentation.screens.home.HomeRoute
import org.koin.core.component.inject

class SplashViewModel(
    private val routeNavigator: RouteNavigator
) : BaseViewModel(), RouteNavigator by routeNavigator {

    // DI
    private val getCurrencies: GetCurrenciesUseCase by inject()

    init {
        launchIO {
            getCurrencies.execute()
            popToRoute(
                destination = HomeRoute.route,
                popUpTo = SplashRoute.route,
                inclusive = true
            )
        }
    }
}
