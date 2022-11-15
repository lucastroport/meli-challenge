package com.lucas.yourmarket.data.storage.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.domain.model.State
import com.lucas.yourmarket.data.storage.dao.StateDao
import com.lucas.yourmarket.data.storage.datastore.interfaces.StateLocalDatastore

class StateLocalDatastoreImpl(
    private val stateDao: StateDao
): StateLocalDatastore {

    companion object {
        private val CLASS_TAG = StateLocalDatastoreImpl::class.qualifiedName
    }

    override suspend fun getStateById(id: String): State? {
        return try {
            stateDao.getById(id)
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
            null
        }
    }

    override suspend fun storeState(state: State) {
        try {
            stateDao.insert(state)
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
        }
    }

    override suspend fun clearAllStates() {
        try {
            stateDao.clearAll()
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
        }
    }
}
