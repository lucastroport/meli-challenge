package com.lucas.yourmarket.domain.usecases

import com.lucas.yourmarket.domain.model.response.Status
import org.koin.core.component.KoinComponent

interface BaseUseCase<Req : BaseUseCase.Request, Resp: BaseUseCase.Response> : KoinComponent {
    open class Request
    open class Response

    suspend fun execute(request: Req? = null): Status<Resp>

    suspend fun Req?.failIfNull(block: suspend (Req) -> Status<Resp>): Status<Resp> =
        this?.let { block(it) } ?: Status.Failure.ExceptionFailure(java.lang.IllegalArgumentException("Request must not be null for this Use Case"))
}
