package com.alejandro.monkeyfilmapp.home.data

import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.core.network.RetrofitHelper
import com.alejandro.monkeyfilmapp.home.data.network.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieRepository {
     suspend fun obtainMovies() : ArrayList<MovieModel>{
         val itemsArray : ArrayList<MovieModel> = ArrayList()
         val service = MovieService()
         CoroutineScope(Dispatchers.IO).launch {
             val items = service.doMovies()

             withContext(Dispatchers.IO){
                 for(i in 0 until items.count()){
                     val id = items[i].id
                     val title = items[i].title
                     val cartel = items[i].cartel
                     val desc = items[i].description
                     val score = items[i].score
                     val favourites = items[i].favorite
                     val category = items[i].category
                     val genre = items[i].genre
                     itemsArray.add(MovieModel(id, title, desc, cartel, score, favourites,category, genre))
                 }
                 }
             }
         return itemsArray
         }
    }