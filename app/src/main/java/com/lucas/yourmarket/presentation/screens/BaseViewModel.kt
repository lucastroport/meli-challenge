package com.lucas.yourmarket.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.lucas.yourmarket.domain.util.coroutines.DispatcherProvider
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

    //  Loading Spinner
    val fullScreenLoading = MutableLiveData(false)
    val localLoading = MutableLiveData(false)

    // A different indicator can be provided to this wrapper so UI can implement local spinners when needed.
    //  Otherwise this will default to using above loading indicator (which blocks UI).
    suspend fun withLoadingSpinner(
        spinner: MutableLiveData<Boolean> = fullScreenLoading,
        block: suspend () -> Unit
    ) {
        spinner.postValue(true)
        block()
        spinner.postValue(false)
    }
}
