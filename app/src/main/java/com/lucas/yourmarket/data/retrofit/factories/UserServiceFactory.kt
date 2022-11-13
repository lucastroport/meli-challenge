package com.lucas.yourmarket.data.retrofit.factories

import com.lucas.yourmarket.data.retrofit.builder.ServiceFactory
import com.lucas.yourmarket.data.retrofit.services.UserService

class UserServiceFactory : ServiceFactory<UserService>(){
    override fun produce(): UserService = retrofit.create(UserService::class.java)
}
