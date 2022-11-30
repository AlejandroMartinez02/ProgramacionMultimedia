package com.alejandro.monkeyfilmapp.login.data

import com.alejandro.monkeyfilmapp.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun doLogin() : Boolean{
        return api.doLogin()
    }
}