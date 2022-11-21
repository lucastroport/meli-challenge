package com.lucas.yourmarket.presentation.ui.screenUtils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lucas.yourmarket.presentation.ui.theme.Dimens
import com.lucas.yourmarket.presentation.ui.theme.YourMarketTypography
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor

data class ProductItemCardState(
    val key: String,
    val name: String?,
    val onCardClicked: (String) -> Unit = {},
    val price: String? = null,
    val hasFreeShipping: Boolean? = null,
    val thumbnailUrl: String? = null
)

@Composable
fun ProductItemCard(
    state: ProductItemCardState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { state.onCardClicked.invoke(state.key) },
        elevation = Dimens.grid_0_25,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.grid_1),
        ) {
            AsyncImage(
                model = state.thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(
                        RoundedCornerShape(corner = CornerSize(Dimens.grid_2))
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Dimens.grid_1)
            ) {
                Box(modifier = Modifier
                    .padding(bottom = Dimens.grid_1, end = Dimens.grid_6)
                ) {
                    state.name?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = YourMarketTypography.body1.copy(
                                fontSize = 12.sp,
                                lineHeight = 12.sp
                            )
                        )
                    }
                }
                state.price?.let {
                    Text(
                        text = state.price,
                        style = YourMarketTypography.h6.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
                state.hasFreeShipping?.let { hasFreeShipping ->
                    if (hasFreeShipping) {
                        Text(
                            text = stringResource(id = R.string.free_shipping_label),
                            color = YourMarketColor.LighterGreen,
                            style = YourMarketTypography.body1.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 9.sp
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
fun ProductItemCardPreview() {
    ProductItemCard(state =
        ProductItemCardState(
            thumbnailUrl = "https://example.jpg",
            key = "CODE",
            name = "Apple Iphone 13 (128 GB) - Midnight Blue",
            onCardClicked = {},
            price = "U\$S 1.090",
            hasFreeShipping = true
        )
    )
}