package com.alejandro.monkeyfilmapp.home.data.network

import com.alejandro.monkeyfilmapp.home.data.MovieModel
import com.alejandro.monkeyfilmapp.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofitHelper()

    suspend fun doMovies() : List<MovieModel> {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieClient::class.java).getMovies()
            response.body()?.arrayMovies ?: emptyList()
        }
    }
}