package com.lucas.yourmarket.domain.interactors

import android.util.Log
import com.lucas.yourmarket.data.storage.datastore.implementations.StateLocalDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.RemoteKeyDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.StateLocalDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.UserLocalDatastore
import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.usecases.BaseUseCase
import com.lucas.yourmarket.domain.usecases.ClearStorageUseCase
import org.koin.core.component.inject

class ClearStorageInteractor : ClearStorageUseCase {

    companion object {
        private val CLASS_TAG = ClearStorageInteractor::class.qualifiedName
    }
    // DI
    private val remoteKeyDatastore: RemoteKeyDatastore by inject()
    private val stateLocalDatastore: StateLocalDatastore by inject()
    private val productLocalDatastore: ProductLocalDatastore by inject()
    private val userLocalDatastore: UserLocalDatastore by inject()

    override suspend fun execute(request: BaseUseCase.Request?): Status<BaseUseCase.Response> {
        return try {
            remoteKeyDatastore.clearAllRemoteKeys()
            stateLocalDatastore.clearAllStates()
            productLocalDatastore.clearAllProducts()
            userLocalDatastore.clearAllUsers()

            Status.Success()
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
            Status.Failure.ExceptionFailure(e)
        }
    }
}