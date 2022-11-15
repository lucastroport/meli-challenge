package com.lucas.yourmarket.data.remote.services

import com.lucas.yourmarket.domain.model.State
import com.lucas.yourmarket.data.remote.Endpoint.ENDPOINT_GET_STATE
import retrofit2.http.GET
import retrofit2.http.Path

interface StateService {
    @GET(ENDPOINT_GET_STATE)
    suspend fun fetchState(@Path("stateId") stateId: String): State
}