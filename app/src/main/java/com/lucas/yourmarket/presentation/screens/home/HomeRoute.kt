package com.lucas.yourmarket.presentation.screens.home

import androidx.compose.runtime.Composable
import com.lucas.yourmarket.presentation.converters.toState
import com.lucas.yourmarket.presentation.navigation.NavRoute
import org.koin.androidx.compose.koinViewModel

object HomeRoute : NavRoute<HomeViewModel> {

    override val route = "home/"

    @Composable
    override fun viewModel(): HomeViewModel = koinViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) = HomeScreenContent(state = viewModel.toState())
}