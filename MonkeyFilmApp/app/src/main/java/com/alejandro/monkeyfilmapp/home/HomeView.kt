package com.alejandro.monkeyfilmapp.home

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.ui.screens.MonkeyMainScaffold

@Composable
fun HomeScreen(homeModelView: HomeModelView, navigationController: NavHostController) {
    val peliculas = homeModelView.peliculas.observeAsState()
    MonkeyMainScaffold(peliculas, homeModelView, navigationController)
}





