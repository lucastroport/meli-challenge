package com.lucas.yourmarket.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lucas.yourmarket.R

// Fonts
val OpenSans = FontFamily(
    Font(R.font.open_sans),
    Font(R.font.open_sans_semibold, FontWeight.SemiBold),
    Font(R.font.open_sans_bold, FontWeight.Bold),
)

val PtSans = FontFamily(
    Font(R.font.pt_sans),
    Font(R.font.pt_sans_bold, FontWeight.Bold)
)

val YourMarketTypography = Typography(
    // This is specific to the header in the Welcome Screen.
    h3 = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        lineHeight = 35.sp,
        color = YourMarketColor.DarkGray,
    ),


    // Used for non bold title on simple style screens
    h5 = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        lineHeight = 27.sp,
        color = YourMarketColor.DarkGray,
    ),

    // Corresponds to Text.Header in styles.xxl
    h6 = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        color = YourMarketColor.DarkGray,
    ),
    // Corresponds to
    subtitle1 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp,
        color = YourMarketColor.Stone,
    ),

    subtitle2 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = YourMarketColor.DarkGray,
    ),

    // Corresponds to Text.Regular in styles.xxl
    body1 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        color = YourMarketColor.Stone,
    ),

    // Used for disclaimers
    body2 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 19.sp,
        color = YourMarketColor.Stone,
    ),

    button = TextStyle(
        fontSize = 15.sp,
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        color = Color.White
    ),

    // Used for pill text
    overline = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 15.5.sp
    )
)

val Typography.subtitle2Normal
    @Composable get() = subtitle2.copy(
        fontWeight = FontWeight.Normal
    )

val Typography.placeHolderHint
    @Composable get() = YourMarketTypography.body1.copy(
        color = YourMarketColor.DarkGray.copy(alpha = .5f),
    )

@Suppress("unused")
fun Typography.instructionStyle() = TextStyle(
    fontSize = 15.sp,
    fontFamily = OpenSans,
    fontWeight = FontWeight.Normal,
    color = YourMarketColor.Stone
)

fun Typography.screenResult() = TextStyle(
    fontSize = 25.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = PtSans,
    lineHeight = 54.sp,
    color = YourMarketColor.LightGray
)
