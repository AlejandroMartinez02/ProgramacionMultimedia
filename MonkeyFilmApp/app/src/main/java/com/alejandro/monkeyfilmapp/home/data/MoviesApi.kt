package com.alejandro.monkeyfilmapp.home.data

import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.core.network.MoviesRetrofit
import com.alejandro.monkeyfilmapp.home.data.network.MoviesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoviesApi {
    var itemsArray : ArrayList<MovieModel> = ArrayList()

     suspend fun obtainMovies(){
        val service = MoviesRetrofit.getMoviesRetrofit().create(MoviesService::class.java)
         CoroutineScope(Dispatchers.IO).launch {
             val response = service.getMovies()

             withContext(Dispatchers.Unconfined){
                 if(response.isSuccessful){
                    val items = response.body()
                     if(items != null){
                         for(i in 0 until items.arrayMovies.count()){
                             val id = items.arrayMovies[i].id
                             val title = items.arrayMovies[i].title
                             val cartel = items.arrayMovies[i].cartel
                             val desc = items.arrayMovies[i].description
                             val score = items.arrayMovies[i].score
                             val favourites = items.arrayMovies[i].favorite
                             val genre = items.arrayMovies[i].genre
                             itemsArray.add(MovieModel(id, title, desc, cartel, score, favourites, genre))
                         }
                     }
                 }
             }
         }
    }
}