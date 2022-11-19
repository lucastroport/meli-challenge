package com.lucas.yourmarket.presentation.screens.splash

import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel

class SplashViewModel(
    private val routeNavigator: RouteNavigator
) : BaseViewModel(), RouteNavigator by routeNavigator {

}