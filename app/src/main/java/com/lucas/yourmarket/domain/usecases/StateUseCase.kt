package com.lucas.yourmarket.domain.usecases

import com.lucas.yourmarket.domain.model.State

interface StateUseCase : BaseUseCase<StateUseCase.Request, StateUseCase.Response> {
    data class Response(val state: State?) : BaseUseCase.Response()
    data class Request(val stateId: String) : BaseUseCase.Request()
}
