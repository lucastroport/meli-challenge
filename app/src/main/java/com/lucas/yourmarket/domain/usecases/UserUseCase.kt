package com.lucas.yourmarket.domain.usecases

import com.lucas.yourmarket.domain.model.User

interface UserUseCase : BaseUseCase<UserUseCase.Request, UserUseCase.Response> {
    data class Response(val user: User?) : BaseUseCase.Response()
    data class Request(val userId: Long) : BaseUseCase.Request()
}