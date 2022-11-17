package com.lucas.yourmarket.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lucas.yourmarket.presentation.ui.theme.Dimens

@Composable
fun Modifier.gutterPadding() = padding(start = Dimens.grid_2_5, end = Dimens.grid_2_5)