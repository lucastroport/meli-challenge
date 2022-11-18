package com.lucas.yourmarket.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.ui.gutterPadding
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState
import com.lucas.yourmarket.presentation.ui.screenUtils.PageIndicators
import com.lucas.yourmarket.presentation.ui.theme.*

data class ProductDetailState(
    val name: State<String?>,
    val price: State<String?>,
    val hasWarranty: State<Boolean?>,
    val warranty: State<String?>,
    val imageThumbnailUrl: State<String?>,
    val imagesUrls: State<List<String>?>,
    val isFreeShipping: State<Boolean?>,
    val sellerName: State<String?>,
    val currencySymbol: State<String?>,
    val sellerLocation: State<String?>,
    val isPlatinumUser: State<Boolean?>,
    val reputation: State<String?>,
    val productCondition: State<String?>,
    val soldQuantity: State<Int?>
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailScreenContent(
    state: ProductDetailState
) {
    val images = state.imagesUrls.value
    val pagerState = rememberPagerState()
    val imageUrl = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = Dimens.grid_3)
            .fillMaxHeight()
    ){
        Column(
            modifier = Modifier
                .gutterPadding()
        ) {
            state.productCondition.value?.let { condition ->
                state.soldQuantity.value?.let { quantity ->
                    Text(
                        modifier = Modifier
                            .padding(bottom = Dimens.grid_1),
                        text = condition +
                                if (quantity > 0)
                                    " | $quantity sold"
                                else
                                    "",
                        style = YourMarketTypography.body2.copy(
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    )
                }
            }

            state.name.value?.let {
                Text(
                    text = it,
                    style = YourMarketTypography.subtitle2Normal.copy(
                        fontSize = 15.sp
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = Dimens.grid_2)
                .gutterPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            images?.let { items ->
                HorizontalPager(
                    state = pagerState,
                    count = items.size,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) { page ->

                    imageUrl.value = items[page]
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Box(contentAlignment = Alignment.BottomCenter) {

                            val painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = imageUrl.value)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        placeholder(R.drawable.image_placeholder)
                                        scale(coil.size.Scale.FILL)
                                    }).build()
                            )
                            Image(
                                painter = painter, contentDescription = "", Modifier
                                    .padding(top = Dimens.grid_1, bottom = Dimens.grid_1)
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                PageIndicators(listSize = items.size, pageIndex = pagerState.currentPage)
            }

        }
        Column(
            modifier = Modifier
                .gutterPadding()
        ) {
            state.currencySymbol.value?.let { currency ->
                state.price.value?.let { price ->
                    Text(
                        text = "$currency $price",
                        style = YourMarketTypography.h3.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            state.isFreeShipping.value?.let { freeShipping ->
                if (freeShipping) {
                    Text(
                        modifier = Modifier
                            .padding(top = Dimens.grid_0_25),
                        text = stringResource(id = R.string.free_shipping_label),
                        color = YourMarketColor.LighterGreen,
                        style = YourMarketTypography.body1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    )
                }
            }
            state.sellerName.value.let {
                Text(
                    modifier = Modifier
                        .padding(top = Dimens.grid_1),
                    text = "Sold by $it",
                    style = YourMarketTypography.body2.copy(
                        fontSize = 12.sp
                    )
                )
            }
            state.hasWarranty.value?.let { hasWarranty ->
                state.warranty.value?.let {
                    if (hasWarranty) {
                        state.warranty.value?.let { warranty ->
                            Text(
                                text = warranty,
                                style = YourMarketTypography.body2.copy(
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                }
            }
        }

        Divider(
            modifier = Modifier
                .padding(top = Dimens.grid_3, bottom = Dimens.grid_3),
            color = Color.LightGray,
            thickness = 1.dp
        )

        SellerInformation(
            location = state.sellerLocation.value,
            reputation = state.reputation.value,
            isPlatinumUser = state.isPlatinumUser.value
        )
    }
}

@Composable
fun SellerInformation(
    location: String?,
    reputation: String?,
    isPlatinumUser: Boolean?
) {
    Column(
        modifier = Modifier
            .gutterPadding(),
        verticalArrangement = Arrangement.spacedBy(Dimens.grid_2)
    ) {
        Text(
            text = stringResource(id = R.string.seller_information_label),
            style = YourMarketTypography.h6.copy(
                fontWeight = FontWeight.Normal
            )
        )
        location?.let {
            InfoItem(
                title = stringResource(id = R.string.location_label),
                description = it,
                iconResId = R.drawable.ic_location
            )
        }
        reputation?.let { 
            InfoItem(
                title = stringResource(id = R.string.reputation_label),
                description = it,
                iconResId = R.drawable.ic_star
            )
        }
        isPlatinumUser?.let {
            if (isPlatinumUser) {
                InfoItem(
                    title = stringResource(id = R.string.platinum_label),
                    titleColor = YourMarketColor.LighterGreen,
                    description = stringResource(id = R.string.platinum_description),
                    iconResId = R.drawable.ic_medal
                )
            }
        }

    }
}

@Composable
fun InfoItem(
    title: String,
    description: String,
    iconResId: Int,
    titleColor: Color = YourMarketColor.DarkGray
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier.padding(end = Dimens.grid_1),
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .padding(top = Dimens.grid_0_5)
                    .size(Dimens.grid_2),
                painter = painterResource(id = iconResId), 
                contentDescription = "",
            )
        }
        Column() {
            Text(
                text = title,
                style = YourMarketTypography.subtitle2Normal.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = titleColor
                )
            )
            Text(
                text = description,
                style = YourMarketTypography.body2.copy(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ProductDetailScreenPreview(

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