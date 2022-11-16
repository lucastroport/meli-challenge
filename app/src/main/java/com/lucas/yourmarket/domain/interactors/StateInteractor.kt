package com.lucas.yourmarket.domain.interactors

import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.repository.interfaces.StateRepository
import com.lucas.yourmarket.domain.usecases.StateUseCase
import org.koin.core.component.inject

class StateInteractor : StateUseCase {

    // DI
    private val stateRepository: StateRepository by inject()

    override suspend fun execute(request: StateUseCase.Request?): Status<StateUseCase.Response> {
        return request.failIfNull { req ->
            val state = stateRepository.fetchState(req.stateId)
            Status.Success(StateUseCase.Response(state))
        }
    }
}