package com.lucas.yourmarket.data.repository.implementations

import com.lucas.yourmarket.data.model.State
import com.lucas.yourmarket.data.remote.datastore.interfaces.StateRemoteDatastore
import com.lucas.yourmarket.data.repository.interfaces.StateRepository
import com.lucas.yourmarket.data.storage.datastore.interfaces.StateLocalDatastore

class StateRepositoryImpl(
    private val stateLocalDatastore: StateLocalDatastore,
    private val stateRemoteDatastore: StateRemoteDatastore
): StateRepository {
    override suspend fun fetchState(stateId: String): State? {
        val response = stateRemoteDatastore.getStateById(stateId)
        response?.let {
            stateLocalDatastore.storeState(it)
        }
        return stateLocalDatastore.getStateById(stateId)
    }
}
