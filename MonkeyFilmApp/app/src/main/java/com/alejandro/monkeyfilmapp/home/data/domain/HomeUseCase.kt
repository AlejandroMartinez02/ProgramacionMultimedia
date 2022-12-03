package com.alejandro.monkeyfilmapp.home.data.domain

import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.home.data.MovieRepository

class HomeUseCase {
    private  val repository = MovieRepository()

    suspend operator fun invoke() : MutableList<MovieModel>{
        return repository.obtainMovies()
    }
}