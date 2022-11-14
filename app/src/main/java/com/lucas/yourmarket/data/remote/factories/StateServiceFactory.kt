package com.lucas.yourmarket.data.remote.factories

import com.lucas.yourmarket.data.remote.services.StateService

class StateServiceFactory: ServiceFactory<StateService>() {
    override fun produce(): StateService = retrofit.create(StateService::class.java)
}