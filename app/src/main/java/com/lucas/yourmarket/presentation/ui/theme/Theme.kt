package com.lucas.yourmarket.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor.LightYellow
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor.MidDarkYellow
import com.lucas.yourmarket.presentation.ui.unit.Dimensions
import com.lucas.yourmarket.presentation.ui.unit.normalDimensions

private val DarkColorPalette = darkColors(
    primary = LightYellow,
    primaryVariant = MidDarkYellow,
    secondary = MidDarkYellow
)

private val LightColorPalette = lightColors(
    primary = LightYellow,
    primaryVariant = MidDarkYellow,
    secondary = MidDarkYellow
)

private val LocalAppDimens = staticCompositionLocalOf {
    normalDimensions
}

@Composable
fun YourMarketTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object YourMarketTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}

val Dimens: Dimensions
    @Composable
    get() = YourMarketTheme.dimens
