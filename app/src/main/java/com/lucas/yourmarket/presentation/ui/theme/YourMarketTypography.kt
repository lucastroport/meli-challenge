package com.lucas.yourmarket.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

val SfPro = FontFamily(
    Font(R.font.sfpro_display_semibold, FontWeight.SemiBold)
)

val YourMarketTypography = androidx.compose.material.Typography(
    // This is specific to the header in the Welcome Screen.
    h3 = TextStyle(
        fontFamily = PtSans,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        lineHeight = 35.sp,
        color = YourMarketColor.DarkGray,
    ),

    // Used for keypad
    h4 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        lineHeight = 35.sp,
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

val TextStyle.semibold
    @Composable get() = this.copy(fontWeight = FontWeight.SemiBold)

val TextStyle.bold
    @Composable get() = this.copy(fontWeight = FontWeight.Bold)

val Typography.error1
    @Composable get() = YourMarketTypography.body1.copy(
        color = MaterialTheme.colors.error
    )

val Typography.error2
    @Composable get() = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        color = MaterialTheme.colors.error,
    )

val Typography.boxedText
    @Composable get() = TextStyle(
        fontFamily = SfPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        color = YourMarketColor.BlueGrey,
    )

val Typography.hintText
    @Composable get() = YourMarketTypography.body1.copy(
        color = YourMarketColor.Stone.copy(alpha = .5f)
    )

val Typography.placeHolderHint
    @Composable get() = YourMarketTypography.body1.copy(
        color = YourMarketColor.DarkGray.copy(alpha = .5f),
    )

val Typography.hintTextDarkGray50
    @Composable get() = YourMarketTypography.body1.copy(
        color = YourMarketColor.DarkGray.copy(alpha = .5f),
        fontWeight = FontWeight.SemiBold
    )

val Typography.hintTextDarkGray
    @Composable get() = YourMarketTypography.body1.copy(
        color = YourMarketColor.DarkGray,
        fontWeight = FontWeight.SemiBold
    )

val Typography.subtitle2SemiBold
    @Composable get() = subtitle2.copy(
        fontWeight = FontWeight.SemiBold
    )

val Typography.subtitle2Normal
    @Composable get() = subtitle2.copy(
        fontWeight = FontWeight.Normal
    )

val Typography.buttonNormal
    @Composable get() = button.copy(
        fontWeight = FontWeight.Normal
    )

val Typography.buttonSemibold
    @Composable get() = button.copy(
        fontWeight = FontWeight.SemiBold
    )

val Typography.subtitle1SizeFifteen
    @Composable get() = subtitle1.copy(
        fontSize = 15.sp
    )

val Typography.subtitle1Normal
    @Composable get() = subtitle1.copy(
        fontWeight = FontWeight.Normal
    )

val Typography.amountPlaceHolder
    @Composable get() = h4.copy(
        fontSize = 27.sp,
        color = YourMarketColor.DarkGray.copy(alpha = .5f)
    )

val Typography.amountTextStyle
    @Composable get() = h4.copy(
        fontSize = 27.sp,
        color = YourMarketColor.DarkGray,
        textAlign = TextAlign.End
    )

val Typography.disclaimerStyle
    @Composable get() = body2.copy(
        color = YourMarketColor.DarkGray,
        fontStyle = FontStyle.Italic
    )

// Type extensions
@Suppress("unused")
fun Typography.embeddedButton() = TextStyle(
    fontSize = 17.sp,
    fontFamily = OpenSans,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Center
)

@Suppress("unused")
fun Typography.instructionStyle() = TextStyle(
    fontSize = 15.sp,
    fontFamily = OpenSans,
    fontWeight = FontWeight.Normal,
    color = YourMarketColor.Stone
)

fun Typography.tileTitle() = TextStyle(
    fontSize = 18.sp,
    fontFamily = OpenSans,
    lineHeight = 25.sp,
    color = Color.White
)

fun Typography.portfolioValue() = TextStyle(
    fontSize = 39.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = OpenSans,
    lineHeight = 54.sp,
    color = YourMarketColor.DarkGray
)
