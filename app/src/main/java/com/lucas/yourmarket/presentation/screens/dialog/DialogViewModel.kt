package com.lucas.yourmarket.presentation.screens.dialog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import org.koin.core.component.inject
import com.lucas.yourmarket.R

class DialogViewModel(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator
) : BaseViewModel(), RouteNavigator by routeNavigator
{
    // DI
    private val context: Context by inject()

    val title: LiveData<String> = MutableLiveData(DialogRoute.getTitleFrom(savedStateHandle))
    val description: LiveData<String> = MutableLiveData(DialogRoute.getMessageFrom(savedStateHandle))
    val primaryButtonText: LiveData<String> = MutableLiveData(context.getString(R.string.btn_accept_label))

    fun onDismissClicked() {
        routeNavigator.navigateUp()
    }
}
