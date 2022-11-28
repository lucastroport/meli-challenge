package com.lucas.yourmarket.presentation.models

data class DialogUI(
    val title: String,
    val message: String,
    val onPrimaryClicked: () -> Unit = {},
    val onDismissedClicked: () -> Unit = {},
    val primaryButtonText: String? = ""
)
