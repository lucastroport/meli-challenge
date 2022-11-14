package com.lucas.yourmarket.data.remote.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.data.model.State
import com.lucas.yourmarket.data.remote.datastore.interfaces.StateRemoteDatastore
import com.lucas.yourmarket.data.remote.services.StateService

class StateRemoteDatastoreImpl(
    private val stateService: StateService
): StateRemoteDatastore {

    companion object {
        private val CLASS_TAG = StateRemoteDatastoreImpl::class.qualifiedName
    }

    override suspend fun getStateById(stateId: String): State? {
        return try {
            stateService.fetchState(stateId)
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
            null
        }
    }
}