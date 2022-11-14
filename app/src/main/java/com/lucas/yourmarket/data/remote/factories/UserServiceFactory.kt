package com.lucas.yourmarket.data.remote.factories

import com.lucas.yourmarket.data.remote.services.UserService

class UserServiceFactory : ServiceFactory<UserService>(){
    override fun produce(): UserService = retrofit.create(UserService::class.java)
}
