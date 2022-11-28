package com.alejandro.monkeyfilmapp.login.data.network

import com.alejandro.monkeyfilmapp.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofitHelper()

    suspend fun doLogin(user: String, password : String) : Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(LoginClient::class.java).doLogin()
            response.body()?.okLogin ?:false
        }
    }
}