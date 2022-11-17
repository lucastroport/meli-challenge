package com.lucas.yourmarket.presentation.ui.helpers

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

@SuppressLint("UnrememberedMutableState")
@Composable
fun <T> T.wrapInState() = mutableStateOf(this)