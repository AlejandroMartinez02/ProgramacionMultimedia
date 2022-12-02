package com.alejandro.monkeyfilmapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.home.data.MoviesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class HomeModelView : ViewModel() {
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

    private val _titleMovie = MutableLiveData<String>()
    val titleMovie = _titleMovie

    private val _description = MutableLiveData<String>()
    val description = _description

    private val _genre = MutableLiveData<MutableList<String>>()

    private val _genreType = MutableLiveData<String>()
    val genreType = _genreType

    fun changeMovie(title : String, desc : String, genre: String){
        _titleMovie.value = title
        _description.value = desc
        _genreType.value = genre
        comprobateMovie()
    }

    fun addMovie(){
        _peliculas.value?.add(
            MovieModel(
                _peliculas.value!!.last().id,
                _titleMovie.value.toString(),
                _description.value.toString(),
                0,
                50,
                false,
                setGenreList()
            )
        )
        _titleMovie.value = ""
        _description.value = ""
        _genreType.value = ""
        _genre.value?.removeAll(_genre.value!!)
    }

    private val _enabled = MutableLiveData<Boolean>()
    val enabled = _enabled

    private fun comprobateMovie(){
        if(_titleMovie.value!!.isNotEmpty() && genreType.value!!.isNotEmpty() && _description.value!!.isNotEmpty()){
            _enabled.value = true
        }
    }

    fun setGenreList() : List<String> {
        return _genreType.value!!.split(',')
    }
}
