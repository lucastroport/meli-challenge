package com.lucas.yourmarket.presentation.ui.screenUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.ui.theme.YourMarketTypography
import com.lucas.yourmarket.presentation.ui.theme.screenResult

enum class ResultState(
    val iconResourceId: Int,
    val messageResourceId: Int
) {
    NO_RESULTS(iconResourceId = R.drawable.ic_no_results, messageResourceId = R.string.no_results_message),
    TYPE_SOMETHING(iconResourceId = R.drawable.ic_type_something, messageResourceId = R.string.type_something)
}

@Composable
fun ScreenResult(
    resultState: ResultState
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp),
            painter = painterResource(id = resultState.iconResourceId),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = resultState.messageResourceId),
            style = YourMarketTypography.screenResult(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoResultsPreview() {
    ScreenResult(
        ResultState.NO_RESULTS
    )
}

@Preview(showSystemUi = true)
@Composable
fun TypeSomethingPreview() {
    ScreenResult(
        ResultState.TYPE_SOMETHING
    )
}