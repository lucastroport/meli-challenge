package com.lucas.yourmarket.data.storage.datastore.implementations

import android.util.Log
import androidx.paging.PagingSource
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.data.storage.dao.ProductDao
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery

class ProductLocalDatastoreImpl(
    private val productDao: ProductDao
): ProductLocalDatastore {

    companion object {
        private val CLASS_TAG = ProductLocalDatastoreImpl::class.qualifiedName
    }

    override suspend fun storeProduct(product: Product) {
        try {
            productDao.insert(product)
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
        }
    }

    override suspend fun storeProducts(products: List<Product>) {
        try {
            productDao.insertAll(products)
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
        }
    }

    override suspend fun clearAllProducts() {
        try {
            productDao.clearAll()
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
        }
    }

    override suspend fun updateProduct(product: Product) {
        try {
            productDao.update(
                id = product.id,
                pictures = product.pictures,
                warranty = product.warranty ?: "",
                sellerId = product.sellerId ?: 0L,
            )
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
        }
    }

    override suspend fun getProductById(id: String): ProductWithCurrencyQuery? {
        return try {
            productDao.getById(id)
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
            null
        }
    }

    override fun getProducts(): PagingSource<Int, ProductWithCurrencyQuery> = productDao.getPagingSource()

}
