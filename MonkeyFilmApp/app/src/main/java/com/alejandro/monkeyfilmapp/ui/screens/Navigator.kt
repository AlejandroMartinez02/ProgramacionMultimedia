package com.alejandro.monkeyfilmapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alejandro.monkeyfilmapp.favourites.*
import com.alejandro.monkeyfilmapp.home.*
import com.alejandro.monkeyfilmapp.login.ui.*
import com.alejandro.monkeyfilmapp.register.*

@Composable
fun MonkeyNavigator(){
    val navigationController = rememberNavController()
    val login = LoginViewModel()
    NavHost(navController = navigationController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginScreen(login,navigationController)
        }
        composable(route = Routes.Home.route) {
            HomeScreen(HomeModelView(),navigationController = navigationController)
        }
        composable(route = Routes.Favourites.route){
            Favourites(FavouritesModelView(), navigationController)
        }
        composable(route = Routes.Register.route){
            RegisterScreen(RegisterModelView(), navigationController)
        }
        composable(route = Routes.ForwardPassword.route){
            DialogRememberPassword(login, navigationController)
        }
    }
}



