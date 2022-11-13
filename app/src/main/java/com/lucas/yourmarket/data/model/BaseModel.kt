package com.lucas.yourmarket.data.model

import android.util.Log

abstract class BaseModel {

    companion object {
        private const val TAG_FIELD_ERROR = "MANDATORY_FIELDS"
    }

    abstract fun getEntityName(): String

    abstract fun mandatoryFields(): Map<String, Any?>

    fun containsMandatoryFields(): Boolean {
        val nullParams = mandatoryFields().filter { it.value == null }.map { it.key }

        if (nullParams.isNotEmpty()) {
            val exception = IllegalArgumentException(
                "(${getEntityName()}) - Unable to map data: ${nullParams.first()} field not found."
            )
            Log.e(TAG_FIELD_ERROR, Log.getStackTraceString(exception))
            return false
        }
        return true
    }
}
