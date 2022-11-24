package com.lucas.yourmarket.presentation.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Navigator to use when initiating navigation from a ViewModel.
 */
interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(destination: String, popUpTo: String, inclusive: Boolean)
    fun navigateToRoute(route: String)

    val navigationState: StateFlow<NavigationState>
}

class RouteNavigatorImplementation : RouteNavigator {

    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        // clear navigation state, if state is the current state:
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(destination: String, popUpTo: String, inclusive: Boolean) = navigate(
        NavigationState.PopToRoute(
            destination = destination,
            popUpTo = popUpTo,
            inclusive = inclusive
        )
    )

    override fun navigateUp() = navigate(NavigationState.NavigateUp())

    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))

    private fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}
