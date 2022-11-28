package com.lucas.yourmarket.presentation.ui.screenUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    input: String?,
    onSearchPressed: () -> Unit,
    onInputChange: (String) -> Unit = {}
) {
    var isTextFieldFocused: Boolean by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.grid_4))
            .run {
                border(
                    width = when {
                        isTextFieldFocused -> Dimens.grid_0_25
                        else -> 1.dp
                    },
                    color = when {
                        isTextFieldFocused -> YourMarketColor.MidBlue
                        else -> YourMarketColor.LightGray
                    },
                    shape = RoundedCornerShape(Dimens.grid_4)
                )
            }
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = input ?: "",
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .size(YourMarketTheme.dimens.grid_4)
                        .offset((4).dp),
                    contentDescription = "",
                    painter = painterResource(id = R.drawable.ic_icon_search)
                )
            },
            onValueChange = onInputChange,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearchPressed.invoke()
                }
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_placeholder),
                    style = YourMarketTypography.placeHolderHint,
                    color = Color.Unspecified,
                    maxLines = 1
                )
            },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically)
                .onFocusChanged { isTextFieldFocused = it.isFocused  }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar(
        input = "Iphone",
        onSearchPressed = {},
        onInputChange = {}
    )
}
