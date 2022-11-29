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
    val register = RegisterModelView()
    val login = LoginViewModel()
    val main = HomeModelView()
    NavHost(navController = navigationController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginScreen(login,navigationController)
        }
        composable(route = Routes.Home.route) {
            HomeScreen(main,navigationController = navigationController)
        }
        composable(route = Routes.Favourites.route){
            Favourites(main, navigationController)
        }
        composable(route = Routes.Register.route){
            RegisterScreen(register, navigationController)
        }
        composable(route = Routes.ForwardPassword.route){
            DialogRememberPassword(login, navigationController)
        }
    }
}




