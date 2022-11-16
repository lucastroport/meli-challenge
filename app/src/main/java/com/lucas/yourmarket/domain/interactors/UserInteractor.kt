package com.lucas.yourmarket.domain.interactors

import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.domain.repository.interfaces.UserRepository
import com.lucas.yourmarket.domain.usecases.UserUseCase
import org.koin.core.component.inject

class UserInteractor : UserUseCase {

    // DI
    private val userRepository: UserRepository by inject()

    override suspend fun execute(request: UserUseCase.Request?): Status<UserUseCase.Response> {
        return request.failIfNull { req ->
            val user = userRepository.fetchUser(req.userId)
            Status.Success(UserUseCase.Response(user))
        }
    }
}