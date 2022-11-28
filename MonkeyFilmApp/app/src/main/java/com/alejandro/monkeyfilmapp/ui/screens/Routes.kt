package com.alejandro.monkeyfilmapp.ui.screens

sealed class Routes(val route : String){
    object Login : Routes("LOGIN")
    object Register : Routes("REGISTER")
    object Home : Routes("HOME")
    object Favourites : Routes("FAVOURITES")
    object ForwardPassword : Routes("FORWARDPASSWORD")
}
