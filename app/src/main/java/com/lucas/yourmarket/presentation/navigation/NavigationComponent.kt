package com.lucas.yourmarket.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lucas.yourmarket.presentation.screens.splash.SplashRoute

@Composable
fun NavigationComponent(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = SplashRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        SplashRoute.composable(this, navHostController)
    }
}