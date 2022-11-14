package com.lucas.yourmarket.data.repository.interfaces

import com.lucas.yourmarket.data.model.State

interface StateRepository {
    suspend fun fetchState(stateId: String): State?
}
