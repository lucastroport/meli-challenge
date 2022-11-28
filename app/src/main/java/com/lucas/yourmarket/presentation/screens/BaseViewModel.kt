package com.lucas.yourmarket.presentation.screens

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.util.coroutines.DispatcherProvider
import com.lucas.yourmarket.presentation.models.DialogUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.lucas.yourmarket.R
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.dialog.DialogRoute

abstract class BaseViewModel : ViewModel(), KoinComponent {

    companion object {
        private const val TAG_UI_ERROR = "ERROR_UI"
    }

    // DI
    private val dispatcherProvider: DispatcherProvider by inject()

    // Helper functions to avoid needing downcast declarations for public MutableLiveData
    protected fun <T> LiveData<T>.postValue(value: T?) = (this as? MutableLiveData<T>)?.postValue(value)

    // Helper function to launch a coroutine using IO dispatcher
    fun launchIO(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(context = dispatcherProvider.IO, block = block)

    //  Loading Spinner
    val fullScreenLoading = MutableLiveData(false)
    var uiCallback: UiCallback? = null

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
            is Status.Failure -> handleErrorUI(resp)
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
    private fun handleErrorUI(error: Status.Failure?) {
        val context: Context by inject()
        when (error) {
            is Status.Failure.GeneralFailure -> Log.e(TAG_UI_ERROR, error.message)
            is Status.Failure.ExceptionFailure -> Log.getStackTraceString(error.e)
            else -> Log.e(TAG_UI_ERROR, "Status Failure $error")
        }
        uiCallback?.handleError(
            DialogUI(
                message = if (error is Status.Failure.GeneralFailure) error.message else context.getString(R.string.generic_error_message),
                title = context.getString(R.string.generic_error_title),
                primaryButtonText = context.getString(R.string.btn_accept_label)
            )
        )
    }

    fun showRetryErrorDialog(
        routeNavigator: RouteNavigator?,
        dialog: DialogUI?,
        onRetry: () -> Unit
    ) {
        val context: Context by inject()
        routeNavigator?.navigateToRoute(
            DialogRoute.show(
                dialogTitle = dialog?.title ?: context.getString(R.string.generic_error_title),
                dialogMessage = dialog?.message ?: context.getString(R.string.generic_error_message),
                onPrimaryClicked = onRetry,
                primaryButtonText = context.getString(R.string.btn_retry_label)
            )
        )
    }

    interface UiCallback {
        fun handleError(dialogInfo: DialogUI?)
    }
}
