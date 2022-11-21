package com.lucas.yourmarket.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.lucas.yourmarket.presentation.ui.gutterPadding
import com.lucas.yourmarket.presentation.ui.theme.Dimens
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.models.ProductUI
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState
import com.lucas.yourmarket.presentation.ui.screenUtils.*
import com.lucas.yourmarket.presentation.ui.theme.YourMarketTypography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

data class HomeScreenState(
    val onSearchEnter: () -> Unit,
    val searchInput: State<String?>,
    val loading: State<Boolean?>,
    val onEntryChanged: (String) -> Unit,
    val onItemClicked: (String) -> Unit = {},
    val products: MutableStateFlow<PagingData<ProductUI>> = MutableStateFlow(PagingData.from(
        emptyList()
    ))
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
                onSearchPressed = state.onSearchEnter,
                onInputChange = state.onEntryChanged
            )
        }

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
            state.loading.value?.let { loading ->
                SearchContent(
                    items = state.products.collectAsLazyPagingItems(),
                    onItemClicked = state.onItemClicked,
                    isLoading = loading
                )
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
    items: LazyPagingItems<ProductUI>,
    onItemClicked: (String) -> Unit,
    isLoading: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement =
            if (isLoading)
                Arrangement.spacedBy(Dimens.grid_2)
            else
                Arrangement.spacedBy(Dimens.grid_0)
    ) {
        isLoading.let { loading ->
            if (loading) {
                repeat(5) {
                    item {
                        LoadingShimmerEffect()
                    }
                }
            } else {
                items(
                    items = items
                ) { product ->
                    product?.let {
                        ProductItemCard(state =
                            ProductItemCardState(
                                name = it.name,
                                key = it.id,
                                onCardClicked = { productId -> onItemClicked(productId) },
                                price = "${it.currencySymbol} ${it.price}",
                                hasFreeShipping = it.isFreeShipping,
                                thumbnailUrl = it.imageThumbnailUrl
                            )
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(state =
        HomeScreenState(
            searchInput = "".wrapInState(),
            products = MutableStateFlow(PagingData.from(
                listOf(
                    ProductUI(
                        id = "CODE",
                        name = "Piano Digital Artesia Performer Black",
                        price = "379",
                        currencySymbol = "U\$S",
                        imageThumbnailUrl = "someUrl",
                        isFreeShipping = true
                    )
                )
            )),
            loading = false.wrapInState(),
            onSearchEnter = {},
            onEntryChanged = {}
        )
    )
}

