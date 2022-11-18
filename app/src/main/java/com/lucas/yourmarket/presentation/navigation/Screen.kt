package com.lucas.yourmarket.presentation.navigation

sealed class Screen(val route: String){

    companion object {
        const val HOME_SCREEN = "home_screen"
        const val PRODUCT_DETAIL_SCREEN = "product_detail_screen"
        const val SPLASH_SCREEN = "splash_screen"
    }
    object Home: Screen(HOME_SCREEN)
    object ProductDetail: Screen(PRODUCT_DETAIL_SCREEN)
    object Splash: Screen(SPLASH_SCREEN)
}