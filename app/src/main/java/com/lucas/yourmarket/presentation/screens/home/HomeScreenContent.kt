package com.lucas.yourmarket.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
import kotlinx.coroutines.flow.flowOf

data class HomeScreenState(
    val onSearchEnter: () -> Unit,
    val searchInput: State<String?>,
    val loading: State<Boolean?>,
    val onEntryChanged: (String) -> Unit,
    val onItemClicked: (String) -> Unit = {},
    val products: LazyPagingItems<ProductUI>,
    val noResults: State<Boolean?>,
    val isFirstLoaded: State<Boolean?>
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
            state.isFirstLoaded.value?.let { firstLoad ->
                if (firstLoad) {
                    ScreenResult(resultState = ResultState.TYPE_SOMETHING)
                } else {
                    state.loading.value?.let { loading ->
                        state.noResults.value?.let { noResults ->
                            SearchContent(
                                items = state.products,
                                onItemClicked = state.onItemClicked,
                                isLoading = loading,
                                noResults = noResults
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem() {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
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
}

@Composable
fun SearchContent(
    items: LazyPagingItems<ProductUI>?,
    onItemClicked: (String) -> Unit,
    isLoading: Boolean,
    noResults: Boolean
) {
    if(!noResults) {
        val foundResults = remember { mutableStateOf(false) }
        if (foundResults.value) {
            Text(
                modifier = Modifier
                    .padding(bottom = Dimens.grid_2, top = Dimens.grid_2)
                    .fillMaxWidth()
                    .gutterPadding(),
                text = stringResource(id = R.string.search_results_label),
                style = YourMarketTypography.subtitle2
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(
                    top =
                    if (foundResults.value)
                        Dimens.grid_0
                    else
                        Dimens.grid_2
                ),
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
                    items?.let { products ->
                        foundResults.value = products.itemCount > 0
                        items(
                            items = products
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
    } else {
        ScreenResult(ResultState.NO_RESULTS)
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(state =
        HomeScreenState(
            searchInput = "".wrapInState(),
            products
            = flowOf(PagingData.from(
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
            )).collectAsLazyPagingItems(),
            loading = true.wrapInState(),
            onSearchEnter = {},
            onEntryChanged = {},
            noResults = false.wrapInState(),
            isFirstLoaded = true.wrapInState()
        )
    )
}
