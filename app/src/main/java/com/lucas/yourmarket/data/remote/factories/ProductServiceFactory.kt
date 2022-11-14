package com.lucas.yourmarket.data.remote.factories

import com.lucas.yourmarket.data.remote.services.ProductService

class ProductServiceFactory: ServiceFactory<ProductService>() {
    override fun produce(): ProductService = retrofit.create(ProductService::class.java)
}
