package com.alejandro.monkeyfilmapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandro.monkeyfilmapp.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeModelView : ViewModel() {
    private val _peliculas = MutableLiveData<MutableList<MovieModel>>()
    val peliculas = _peliculas
    init {
        val api = MoviesApi()
        CoroutineScope(Dispatchers.Main).launch {
            api.obtainMovies()
            _peliculas.value = api.itemsArray
        }
    }

    private val _showFavourites = MutableLiveData<Boolean>()
    val showFavourites = _showFavourites

    fun changeFavourites(showFavourites : Boolean){
        _showFavourites.value = showFavourites
    }

}