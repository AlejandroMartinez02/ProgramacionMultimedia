package com.alejandro.monkeyfilmapp.home

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.ui.screens.MonkeyMainScaffold

@Composable
fun HomeScreen(homeModelView: HomeModelView, navigationController: NavHostController) {
    MonkeyMainScaffold(homeModelView, navigationController)
}





