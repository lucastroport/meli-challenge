package com.lucas.yourmarket.data.storage.datastore.interfaces

import com.lucas.yourmarket.data.model.State

interface StateLocalDatastore {
    suspend fun getStateById(id: String): State?
    suspend fun storeState(state: State)
    suspend fun clearAllStates()
}