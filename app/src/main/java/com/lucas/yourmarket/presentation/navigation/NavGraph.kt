package com.lucas.yourmarket.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.lucas.yourmarket.presentation.screens.ProductDetailScreenContent
import com.lucas.yourmarket.presentation.screens.ProductDetailState
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState
import com.lucas.yourmarket.presentation.ui.screens.splash.SplashScreenContent
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor

@Composable
fun TopAppBarContentScreen(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = YourMarketColor.MidDarkYellow,
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }

            )
        },
        content = content
    )
}

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route){
            SplashScreenContent()
        }
        composable(route = Screen.ProductDetail.route){
            TopAppBarContentScreen(navController = navController) {
//                ProductDetailScreenContent(state = )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TopAppBarContentPreview(

) {
    TopAppBarContentScreen(
        navController = rememberNavController()
    ) {
        ProductDetailScreenContent(state =
            ProductDetailState(
                imagesUrls = mutableListOf(
                    "url1","url2"
                ).wrapInState(),
                price = "1.115".wrapInState(),
                currencySymbol = "U\$S".wrapInState(),
                hasWarranty = true.wrapInState(),
                warranty = "Factory Warranty: 12 months".wrapInState(),
                sellerLocation = "Villa Biarritz, Montevideo".wrapInState(),
                sellerName = "SHOP_MERCADOUY".wrapInState(),
                productCondition = "New".wrapInState(),
                isPlatinumUser = true.wrapInState(),
                reputation = "Very Good".wrapInState(),
                imageThumbnailUrl = "thumbnailUrl".wrapInState(),
                isFreeShipping = true.wrapInState(),
                name = "Apple Iphone 13 (128 GB) - Midnight Blue".wrapInState(),
                soldQuantity = 10.wrapInState()
            )
        )
    }
}