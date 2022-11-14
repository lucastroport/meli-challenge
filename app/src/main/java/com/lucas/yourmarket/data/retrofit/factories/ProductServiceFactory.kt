package com.lucas.yourmarket.data.retrofit.factories

import com.lucas.yourmarket.data.retrofit.services.ProductService

class ProductServiceFactory: ServiceFactory<ProductService>() {
    override fun produce(): ProductService = retrofit.create(ProductService::class.java)
}
