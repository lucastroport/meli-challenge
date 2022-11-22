package com.lucas.yourmarket.presentation.ui.screenUtils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor

@Composable
fun WithTopAppBar(
    title: String = "",
    navHostController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                backgroundColor = YourMarketColor.MidDarkYellow,
                navigationIcon = if (navHostController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        },
        content = content
    )
}
