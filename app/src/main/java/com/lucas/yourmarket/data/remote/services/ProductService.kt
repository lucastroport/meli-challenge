package com.lucas.yourmarket.data.remote.services

import com.lucas.yourmarket.data.model.Product
import com.lucas.yourmarket.data.model.response.ProductsPagingResponse
import com.lucas.yourmarket.data.remote.Endpoint.ENDPOINT_GET_PRODUCT
import com.lucas.yourmarket.data.remote.Endpoint.ENDPOINT_SEARCH_PRODUCT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET(ENDPOINT_SEARCH_PRODUCT)
    suspend fun fetchProductsPage(@Path("siteId") siteId: String,
                                  @Query("q") query: String,
                                  @Query("offset") offset: Int,
                                  @Query("limit") limit: Int
    ): ProductsPagingResponse

    @GET(ENDPOINT_GET_PRODUCT)
    suspend fun fetchProduct(@Path("itemId") itemId: String): Product
}
