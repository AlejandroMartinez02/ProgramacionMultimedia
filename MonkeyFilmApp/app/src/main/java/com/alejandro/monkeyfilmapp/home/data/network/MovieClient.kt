package com.alejandro.monkeyfilmapp.home.data.network

import com.alejandro.monkeyfilmapp.home.data.network.response.MoviesResponse
import com.alejandro.monkeyfilmapp.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieClient {
    @GET("/v3/ba19d40a-9750-4413-bd70-9c6e703cc9e9/")
    suspend fun getMovies() : Response<MoviesResponse>
}