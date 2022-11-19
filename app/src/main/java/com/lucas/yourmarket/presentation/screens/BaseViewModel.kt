package com.lucas.yourmarket.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.lucas.yourmarket.presentation.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    // DI
    protected val dispatcherProvider: DispatcherProvider by inject()

    // Convenience extension function for LiveEvent
    fun LiveEvent<Unit>.trigger() = postValue(Unit)
    protected fun LiveData<Unit>.trigger() = (this as? LiveEvent<Unit>)?.trigger()

    // Helper function to launch a coroutine using IO dispatcher
    fun launchIO(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(context = dispatcherProvider.IO, block = block)
}
