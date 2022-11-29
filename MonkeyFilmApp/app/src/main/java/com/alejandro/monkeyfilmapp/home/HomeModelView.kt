package com.alejandro.monkeyfilmapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandro.monkeyfilmapp.MediaModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeModelView : ViewModel() {
    private val _peliculas = MutableLiveData<MutableList<MediaModel>>()
    val peliculas = _peliculas
    init {
        val api = MoviesApi()
        CoroutineScope(Dispatchers.Main).launch {
            api.obtainMovies()
            _peliculas.value = api.itemsArray
        }
    }

    fun addPelicula(mediaModel: MediaModel){
        _peliculas.value?.add(mediaModel)
    }
}