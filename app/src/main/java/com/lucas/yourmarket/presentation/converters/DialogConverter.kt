package com.lucas.yourmarket.presentation.converters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.lucas.yourmarket.presentation.screens.dialog.DialogState
import com.lucas.yourmarket.presentation.screens.dialog.DialogViewModel

@Composable
fun DialogViewModel.toState() = DialogState(
    title = title.observeAsState(),
    message = description.observeAsState(),
    onDismissClicked = ::onDismissClicked,
    primaryButtonText = primaryButtonText.observeAsState()
)