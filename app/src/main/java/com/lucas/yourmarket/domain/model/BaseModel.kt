package com.lucas.yourmarket.domain.model

import android.util.Log

abstract class BaseModel {

    companion object {
        private val CLASS_TAG = BaseModel::class.qualifiedName
    }

    abstract fun getEntityName(): String

    abstract fun mandatoryFields(): Map<String, Any?>

    fun containsMandatoryFields(): Boolean {
        val nullParams = mandatoryFields().filter { it.value == null }.map { it.key }

        if (nullParams.isNotEmpty()) {
            val exception = IllegalArgumentException(
                "(${getEntityName()}) - Unable to map data: ${nullParams.first()} field not found."
            )
            Log.e(CLASS_TAG, Log.getStackTraceString(exception))
            return false
        }
        return true
    }
}
