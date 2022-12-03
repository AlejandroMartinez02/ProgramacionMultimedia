package com.alejandro.monkeyfilmapp.home.data.network.response

import com.alejandro.monkeyfilmapp.home.data.MovieModel
import com.google.gson.annotations.SerializedName

data class MoviesResponse(@SerializedName("success") val arrayMovies : ArrayList<MovieModel>)
