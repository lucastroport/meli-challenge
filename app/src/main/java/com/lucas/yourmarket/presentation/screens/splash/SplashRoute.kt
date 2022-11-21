package com.lucas.yourmarket.presentation.screens.splash

import androidx.compose.runtime.Composable
import com.lucas.yourmarket.presentation.navigation.NavRoute
import org.koin.androidx.compose.koinViewModel

object SplashRoute : NavRoute<SplashViewModel> {

    override val route: String = "/splash"

    @Composable
    override fun Content(viewModel: SplashViewModel) = SplashScreenContent()

    @Composable
    override fun viewModel(): SplashViewModel = koinViewModel()

}
