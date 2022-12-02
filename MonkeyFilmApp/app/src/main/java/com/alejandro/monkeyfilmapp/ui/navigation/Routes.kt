package com.alejandro.monkeyfilmapp.ui.navigation

sealed class Routes(val route : String){
    object Login : Routes("LOGIN")
    object Register : Routes("REGISTER")
    object Home : Routes("HOME")
    object AddMovie : Routes("ADDMOVIE")
    object ExpandMovie : Routes("EXPANDMOVIE/{movieid}"){
        fun createRoute(movieid : Int) = "EXPANDMOVIE/$movieid"
    }
}
