package com.lucas.yourmarket.domain.model.response

sealed class Status<out T> {
    data class Success<out T>(val data: T? = null) : Status<T>()

    sealed class Failure : Status<Nothing>() {

        data class ExceptionFailure(val e: Exception) : Failure()
        data class GeneralFailure(val message: String) : Failure()
    }
}