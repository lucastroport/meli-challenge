package com.lucas.yourmarket.domain.usecases

import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery

interface ProductUseCase : BaseUseCase<ProductUseCase.Request, ProductUseCase.Response> {
    data class Response(val product: ProductWithCurrencyQuery?) : BaseUseCase.Response()
    data class Request(val itemId: String) : BaseUseCase.Request()
}
