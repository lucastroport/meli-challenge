package com.lucas.yourmarket.domain.interactors

import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.repository.interfaces.ProductRepository
import com.lucas.yourmarket.domain.usecases.ProductUseCase
import org.koin.core.component.inject

class ProductInteractor : ProductUseCase {

    // DI
    private val productRepository: ProductRepository by inject()

    override suspend fun execute(request: ProductUseCase.Request?): Status<ProductUseCase.Response> {
        return request.failIfNull { req ->
            val product = productRepository.fetchProduct(req.itemId)
            Status.Success(ProductUseCase.Response(product))
        }
    }
}
