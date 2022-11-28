package com.alejandro.pruebas.login.data

import com.alejandro.pruebas.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun doLogin(user : String, password : String) : Boolean{
        return api.doLogin(user,password)
    }
}