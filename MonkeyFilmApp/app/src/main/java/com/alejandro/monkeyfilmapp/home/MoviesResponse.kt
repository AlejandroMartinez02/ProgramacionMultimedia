package com.alejandro.monkeyfilmapp.home

import com.alejandro.monkeyfilmapp.MovieModel
import com.google.gson.annotations.SerializedName

data class MoviesResponse(@SerializedName("success") val arrayMovies : ArrayList<MovieModel>)
