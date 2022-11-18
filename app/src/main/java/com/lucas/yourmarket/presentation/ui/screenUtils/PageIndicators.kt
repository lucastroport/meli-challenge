package com.lucas.yourmarket.presentation.ui.screenUtils

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.lucas.yourmarket.presentation.ui.theme.Dimens
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor

@Composable
fun PageIndicators(
    listSize: Int,
    pageIndex: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        repeat(listSize) {
            Crossfade(targetState = it == pageIndex) { active ->
                val color = if (active) YourMarketColor.LightGray else YourMarketColor.Blue50
                PageIndicator(
                    modifier = Modifier
                        .size(Dimens.grid_2)
                        .padding(horizontal = Dimens.grid_0_5),
                    color = color
                )
            }
        }

    }
}

@Composable
private fun PageIndicator(
    modifier: Modifier = Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = color,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
        radius = size.minDimension / 2
        )
    }
}
