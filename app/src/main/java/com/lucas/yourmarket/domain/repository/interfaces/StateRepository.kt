package com.lucas.yourmarket.domain.repository.interfaces

import com.lucas.yourmarket.domain.model.State

interface StateRepository {
    suspend fun fetchState(stateId: String): State?
}
