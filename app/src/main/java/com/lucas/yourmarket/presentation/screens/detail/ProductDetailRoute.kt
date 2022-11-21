package com.lucas.yourmarket.presentation.screens.detail

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.lucas.yourmarket.presentation.converters.toState
import com.lucas.yourmarket.presentation.navigation.NavRoute
import com.lucas.yourmarket.presentation.navigation.getOrThrow
import org.koin.androidx.compose.koinViewModel

object ProductDetailRoute : NavRoute<ProductDetailViewModel> {

    private const val KEY_PRODUCT_ID = "PRODUCT_ID"
    
    override val route = "product/{$KEY_PRODUCT_ID}/"

    fun get(productId: String): String = route.replace("{$KEY_PRODUCT_ID}", productId)

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<String>(KEY_PRODUCT_ID)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_PRODUCT_ID) { type = NavType.StringType })

    @Composable
    override fun Content(viewModel: ProductDetailViewModel) = ProductDetailScreenContent(state = viewModel.toState())

    @Composable
    override fun viewModel(): ProductDetailViewModel = koinViewModel()
}
