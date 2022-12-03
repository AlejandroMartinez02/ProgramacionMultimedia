package com.alejandro.monkeyfilmapp.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alejandro.monkeyfilmapp.home.data.MovieModel
import com.alejandro.monkeyfilmapp.home.domin.HomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class HomeModelView : ViewModel() {
    private val movies = HomeUseCase()

    private val _peliculas = MutableLiveData<MutableList<MovieModel>>()
    val peliculas = _peliculas
    init {
        CoroutineScope(Dispatchers.Main).launch {
            _peliculas.value = movies()
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
                _peliculas.value!!.last().id + 1,
                _titleMovie.value.toString(),
                _description.value.toString(),
                0,
                50,
                false,
                "Pelicula",
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
