package es.amm.monkeyfilmlab.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import es.amm.monkeyfilmlab.model.Routes

@Composable
fun ScreenOne(NavigationController : NavHostController){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Red)
    ){
        Text(text = "Pantalla 1", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                NavigationController.navigate(Routes.ScreenTwo.route)
            })

    }
}

@Composable
fun ScreenTwo(NavigationController : NavHostController){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Magenta)
    ){
        Text(text = "Pantalla 2", modifier = Modifier
            .align(Alignment.Center)
            .clickable { NavigationController.navigate(Routes.ScreenThree.createRoute(88))})

    }
}

@Composable
fun ScreenThree(NavigationController: NavHostController, score: Int){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Green)
    ){
        Text(text = "Pantalla 3 -> $score", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                NavigationController.navigate(Routes.ScreenFour.createRoute("SALVA"))
            })

    }
}

@Composable
fun ScreenFour(NavigationController: NavHostController, title: String?){
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue)
    ){
        Text(text = "Pantalla 4 + $title", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                NavigationController.navigate(Routes.ScreenOne.route)
            })

    }
}