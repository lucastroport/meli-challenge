package com.lucas.yourmarket.presentation.screens

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.util.coroutines.DispatcherProvider
import com.lucas.yourmarket.presentation.models.DialogUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.lucas.yourmarket.R

abstract class BaseViewModel : ViewModel(), KoinComponent {

    // DI
    private val dispatcherProvider: DispatcherProvider by inject()

    // Convenience extension function for LiveEvent
    fun LiveEvent<Unit>.trigger() = postValue(Unit)
    protected fun LiveData<Unit>.trigger() = (this as? LiveEvent<Unit>)?.trigger()

    // Helper functions to avoid needing downcast declarations for public MutableLiveData
    protected fun <T> LiveData<T>.postValue(value: T?) = (this as? MutableLiveData<T>)?.postValue(value)

    // Helper function to launch a coroutine using IO dispatcher
    fun launchIO(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(context = dispatcherProvider.IO, block = block)

    //  Loading Spinner
    val fullScreenLoading = MutableLiveData(false)
    val localLoading = MutableLiveData(false)
    val showDialogEvent = LiveEvent<DialogUI>()

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

    private fun showDialog(dialog: DialogUI) {
        if (showDialogEvent.value == null) {
            showDialogEvent.postValue(dialog)
        }
    }
    /**
     * Used to reduce boiler plate where calling a UseCase and we need generic error handling
     *
     * Usage:
     * ApiCall.execute().handlingErrors { successResponse ->
     *   // Success logic
     * }
     */
    fun <T> Status<T>.handlingErrors(onSuccess: (Status.Success<T>) -> Unit) {
        when (val resp = this) {
            is Status.Success -> onSuccess(resp)
            is Status.Failure -> handleError(resp)
        }
    }

    /**
     * Used to provide default error handling
     *
     * Usage:
     * when (resp = ApiCall.execute()) {
     *   is Status.Failure -> handleError(resp)
     * }
     */
    private fun handleError(error: Status.Failure?) {
        val context: Context by inject()
        if (error is Status.Failure) {
            showDialog(
                DialogUI(
                    title = context.getString(R.string.generic_error_title),
                    message = context.getString(R.string.generic_error_message)
                )
            )
        }
    }
}
