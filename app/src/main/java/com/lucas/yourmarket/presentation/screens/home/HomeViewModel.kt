package com.lucas.yourmarket.presentation.screens.home

import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel

class HomeViewModel(
    private val routeNavigator: RouteNavigator
) : BaseViewModel(), RouteNavigator by routeNavigator {


}