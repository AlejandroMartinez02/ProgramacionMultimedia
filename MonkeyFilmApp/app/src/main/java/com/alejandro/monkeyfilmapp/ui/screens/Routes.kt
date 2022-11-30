package com.alejandro.monkeyfilmapp.ui.screens

import com.alejandro.monkeyfilmapp.MovieModel

sealed class Routes(val route : String){
    object Login : Routes("LOGIN")
    object Register : Routes("REGISTER")
    object Home : Routes("HOME")
    object AddMovie : Routes("ADDMOVIE")
    object ExpandMovie : Routes("EXPANDMOVIE/{movieid}"){
        fun createRoute(movieid : Int) = "EXPANDMOVIE/$movieid"
    }
}
