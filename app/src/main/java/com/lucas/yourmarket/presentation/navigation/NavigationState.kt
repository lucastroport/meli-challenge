package com.lucas.yourmarket.presentation.navigation

import java.util.*

sealed class NavigationState {
    object Idle : NavigationState()
    data class NavigateToRoute(val route: String, val id: String = UUID.randomUUID().toString()) : NavigationState()
    data class PopToRoute(
        val destination: String,
        val popUpTo: String,
        val inclusive: Boolean,
        val id: String = UUID.randomUUID().toString()
    ) : NavigationState()
    data class NavigateUp(val id: String = UUID.randomUUID().toString()) : NavigationState()
}
