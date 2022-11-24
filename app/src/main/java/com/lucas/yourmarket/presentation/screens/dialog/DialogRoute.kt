package com.lucas.yourmarket.presentation.screens.dialog

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import com.lucas.yourmarket.presentation.converters.toState
import com.lucas.yourmarket.presentation.navigation.NavRoute
import com.lucas.yourmarket.presentation.navigation.getOrThrow
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState
import org.koin.androidx.compose.koinViewModel

object DialogRoute : NavRoute<DialogViewModel> {

    private const val KEY_DIALOG_TITLE = "DIALOG_TITLE"
    private const val KEY_DIALOG_MESSAGE = "DIALOG_MESSAGE"

    // Dialog Callbacks
    private var onPrimaryCallback: () -> Unit = {}

    private var primaryButtonLabel: String = ""

    override val route = "dialog/{$KEY_DIALOG_TITLE}/{$KEY_DIALOG_MESSAGE}"

    fun show(
        dialogTitle: String,
        dialogMessage: String,
        onPrimaryClicked: () -> Unit = {},
        primaryButtonText: String
    ): String {
        this.onPrimaryCallback = onPrimaryClicked
        this.primaryButtonLabel = primaryButtonText

        return route
            .replace("{${KEY_DIALOG_TITLE}}", dialogTitle)
            .replace("{${KEY_DIALOG_MESSAGE}}", dialogMessage)
    }

        fun getTitleFrom(savedStateHandle: SavedStateHandle) =
            savedStateHandle.getOrThrow<String>(KEY_DIALOG_TITLE)

        fun getMessageFrom(savedStateHandle: SavedStateHandle) =
            savedStateHandle.getOrThrow<String>(KEY_DIALOG_MESSAGE)

        @Composable
        override fun viewModel(): DialogViewModel = koinViewModel()

        @Composable
        override fun Content(viewModel: DialogViewModel) = DialogContent(
            viewModel.toState().apply {
                onPrimaryClicked = onPrimaryCallback
                primaryButtonText = primaryButtonLabel.wrapInState()
            }
        )
}