package com.alejandro.monkeyfilmapp.login.data.network

import com.alejandro.monkeyfilmapp.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/4fdefa5a-ee25-439b-b32f-ac7e698b71fd")
    suspend fun doLogin():Response<LoginResponse>
}