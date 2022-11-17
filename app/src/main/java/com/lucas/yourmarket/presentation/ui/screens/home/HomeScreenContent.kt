package com.lucas.yourmarket.presentation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.yourmarket.presentation.ui.gutterPadding
import com.lucas.yourmarket.presentation.ui.theme.Dimens
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.models.ProductUI
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState
import com.lucas.yourmarket.presentation.ui.screenUtils.ProductSearchItem
import com.lucas.yourmarket.presentation.ui.screenUtils.SearchBar
import com.lucas.yourmarket.presentation.ui.theme.YourMarketTypography

data class HomeScreenState(
    val searchInput: State<String?>,
    val products: MutableState<List<ProductUI>?> = mutableStateOf(mutableStateListOf())
)

@Composable
fun HomeScreenContent(
    state: HomeScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = YourMarketColor.LightYellow),
        verticalArrangement = Arrangement.spacedBy(Dimens.grid_3_5)
    ) {
        Column(
            modifier = Modifier.gutterPadding()
        ) {
            HeaderItem()
            SearchBar(
                input = state.searchInput.value,
                onSearchPressed = { },
                onInputChange = { }
            )
        }
        state.products.value?.let {
            Column(
                modifier = Modifier.background(color = Color.White)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = Dimens.grid_2, top = Dimens.grid_2)
                        .fillMaxWidth()
                        .gutterPadding(),
                    text = stringResource(id = R.string.search_results_label),
                    style = YourMarketTypography.subtitle2
                )
                SearchContent(items = it)
            }
        }
    }
}

@Composable
fun HeaderItem() {
    Box(
        modifier = Modifier
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = "logo"
        )
    }
}

@Composable
fun SearchContent(
    items: List<ProductUI>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        items.forEach {
            ProductSearchItem(productUI = it)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(state =
        HomeScreenState(
            searchInput = "".wrapInState(),
            products = mutableListOf(
                ProductUI(
                    name = "Piano Digital Artesia Performer Black",
                    price = "379",
                    currencySymbol = "U\$S",
                    sellerName = "Palacio de la Musica",
                    warranty = "6 meses",
                    hasWarranty = true,
                    imageThumbnailUrl = "someUrl",
                    imagesUrl = listOf(""),
                    isFreeShipping = false,
                    sellerLocation = "Centro, Montevideo",
                    isPlatinumUser = true,
                    reputation = "Excelente",
                    productCondition = "Nuevo"
                )
            ).wrapInState()
        )
    )
}
