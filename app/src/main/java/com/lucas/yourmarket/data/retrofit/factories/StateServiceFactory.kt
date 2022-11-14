package com.lucas.yourmarket.data.retrofit.factories

import com.lucas.yourmarket.data.retrofit.services.StateService

class StateServiceFactory: ServiceFactory<StateService>() {
    override fun produce(): StateService = retrofit.create(StateService::class.java)
}