package com.lucas.yourmarket.domain.repository.implementations

import com.lucas.yourmarket.domain.model.State
import com.lucas.yourmarket.data.remote.datastore.interfaces.StateRemoteDatastore
import com.lucas.yourmarket.domain.repository.interfaces.StateRepository
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
