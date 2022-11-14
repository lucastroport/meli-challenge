package com.lucas.yourmarket.data.remote.services

import com.lucas.yourmarket.data.model.User
import com.lucas.yourmarket.data.remote.Endpoint.ENDPOINT_GET_USER_BY_ID
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET(ENDPOINT_GET_USER_BY_ID)
    suspend fun fetchUser(@Path("userId") userId: Long): User
}
