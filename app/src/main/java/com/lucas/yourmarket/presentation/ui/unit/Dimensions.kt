package com.lucas.yourmarket.presentation.ui.unit

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val grid_0: Dp,
    val grid_0_25: Dp,
    val grid_0_5: Dp,
    val grid_1: Dp,
    val grid_1_5: Dp,
    val grid_2: Dp,
    val grid_2_5: Dp,
    val grid_3: Dp,
    val grid_3_5: Dp,
    val grid_4: Dp,
    val grid_4_5: Dp,
    val grid_5: Dp,
    val grid_5_5: Dp,
    val grid_6: Dp,
    val grid_6_5: Dp,
    val grid_7: Dp,
    val grid_7_5: Dp,
    val grid_8: Dp,
    val grid_9: Dp,
    val grid_10: Dp,
    val plane_0: Dp,
    val plane_1: Dp,
    val plane_2: Dp,
    val plane_3: Dp,
    val plane_4: Dp,
    val plane_5: Dp,
    val minimum_touch_target: Dp = 48.dp,
    val qr_code_size: Dp = 158.dp
)

/**
 * Each layout grid line corresponds to 8dp in normal sized layouts.
 *
 **/
val normalDimensions = Dimensions(
    // Margins
    grid_0 = 0.dp,
    grid_0_25 = 2.dp,
    grid_0_5 = 4.dp,
    grid_1 = 8.dp,
    grid_1_5 = 12.dp,
    grid_2 = 16.dp,
    grid_2_5 = 20.dp,
    grid_3 = 24.dp,
    grid_3_5 = 28.dp,
    grid_4 = 32.dp,
    grid_4_5 = 36.dp,
    grid_5 = 40.dp,
    grid_5_5 = 44.dp,
    grid_6 = 48.dp,
    grid_6_5 = 52.dp,
    grid_7 = 56.dp,
    grid_7_5 = 60.dp,
    grid_8 = 64.dp,
    grid_9 = 68.dp,
    grid_10 = 72.dp,
    // Elevation
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_3 = 4.dp,
    plane_4 = 8.dp,
    plane_5 = 16.dp,
)