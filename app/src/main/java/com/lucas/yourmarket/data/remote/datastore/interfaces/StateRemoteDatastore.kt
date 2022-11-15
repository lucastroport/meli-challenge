package com.lucas.yourmarket.data.remote.datastore.interfaces

import com.lucas.yourmarket.domain.model.State

interface StateRemoteDatastore {
    suspend fun getStateById(stateId: String): State?
}