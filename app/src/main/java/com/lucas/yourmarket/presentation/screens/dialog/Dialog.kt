package com.lucas.yourmarket.presentation.screens.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState
import com.lucas.yourmarket.presentation.ui.theme.Dimens
import com.lucas.yourmarket.presentation.ui.theme.YourMarketColor
import com.lucas.yourmarket.presentation.ui.theme.YourMarketTypography

data class DialogState(
    val title: State<String?> = mutableStateOf(""),
    val message: State<String?> = mutableStateOf(""),
    var onPrimaryClicked: () -> Unit = {},
    var onDismissClicked: () -> Unit = {},
    var primaryButtonText: State<String?> = mutableStateOf("")
)


@Composable
fun DialogContent(
    state: DialogState
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(Dimens.grid_1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            state.title.value?.let { title ->
                TitleAndButton(title = title, onDismissClicked = state.onDismissClicked)
            }
            state.message.value?.let { description ->
                AddBody(description)
            }
            state.primaryButtonText.value?.let { buttonText ->
                BottomButtons(primaryButtonText = buttonText, onPrimaryClicked = state.onPrimaryClicked)
            }
        }
    }
}

@Composable
private fun TitleAndButton(
    title: String,
    onDismissClicked: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(Dimens.grid_2_5),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = YourMarketTypography.h5
            )
            IconButton(modifier = Modifier.then(Modifier.size(Dimens.grid_3)),
                onClick = onDismissClicked
            ) {
                Icon(
                    Icons.Filled.Close,
                    "contentDescription"
                )
            }
        }
        Divider(color = Color.DarkGray, thickness = 1.dp)
    }
}

@Composable
private fun BottomButtons(
    primaryButtonText: String,
    onPrimaryClicked: () -> Unit,
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxWidth(1f)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onPrimaryClicked,
            modifier = Modifier
                .width(100.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = YourMarketColor.MidDarkYellow
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = primaryButtonText,
                color = YourMarketColor.DarkBlue,
                style = YourMarketTypography.button
            )
        }

    }
}

@Composable
private fun AddBody(description: String) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(Dimens.grid_2),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = description,
            style = YourMarketTypography.body1
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DialogPreview() {
    DialogContent(
        DialogState(
            title = stringResource(R.string.generic_error_title).wrapInState(),
            message = stringResource(R.string.generic_error_message).wrapInState(),
            onDismissClicked = { },
            onPrimaryClicked = { },
            primaryButtonText = stringResource(R.string.btn_accept_label).wrapInState()
        )
    )
}
