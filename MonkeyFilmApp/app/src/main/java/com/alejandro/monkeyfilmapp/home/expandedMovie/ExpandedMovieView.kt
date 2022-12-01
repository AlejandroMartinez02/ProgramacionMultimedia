package com.alejandro.monkeyfilmapp.home.expandedMovie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.home.HomeModelView
import com.alejandro.monkeyfilmapp.ui.screens.obtenerFoto
import com.alejandro.monkeyfilmapp.ui.theme.Calibri
import com.alejandro.monkeyfilmapp.ui.theme.azulFondo

@Composable
fun ExpandedMovieView(id : Int, homeModel : HomeModelView, navigationControler : NavHostController){
    val peli by homeModel.peliculas.observeAsState()
    Column(modifier = Modifier
        .background(azulFondo)
        .fillMaxSize()
        .padding(10.dp)){
        IconButton(onClick = { navigationControler.popBackStack() }, modifier = Modifier.width(80.dp).background(
            azulFondo)) {
            Icon(painter = painterResource(com.alejandro.monkeyfilmapp.R.drawable.ic_baseline_arrow_back_24), contentDescription = "Back")
        }
            Image(painter = painterResource(id = obtenerFoto(peli!![id].cartel)), contentDescription = peli!![id].title, modifier = Modifier
                .padding(10.dp)
                .height(400.dp)
                .align(Alignment.CenterHorizontally))
        Row(modifier = Modifier
            .padding(start = 10.dp, top = 4.dp, bottom = 4.dp)
            .align(Alignment.CenterHorizontally)){
            Text(text = peli!![id].title, color = Color.White, fontSize = 24.sp, fontFamily = Calibri)
            Icon(
                imageVector = Icons.Default.Star, contentDescription = "Puntuacion",
                Modifier
                    .size(20.dp)
                    .padding(top = 8.dp),
                tint = Color.Yellow
            )
            Text(text = peli!![id].score.toString(), fontSize = 16.sp, fontFamily = Calibri, color = Color.White,modifier = Modifier.padding(top = 5.dp))
        }
        Text(text = getGenre(peli!![id]), color = Color.White, fontSize = 16.sp, fontFamily = Calibri, modifier = Modifier
            .padding(start = 10.dp, top = 4.dp, bottom = 4.dp)
            .align(Alignment.CenterHorizontally))

        Text(text = peli!![id].description, color = Color.White, fontSize = 16.sp, fontFamily = Calibri, modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp), textAlign = TextAlign.Justify)



    }


    }



fun getGenre(pelicula: MovieModel) : String{
    var genre = " | "
    for(i in 0 until pelicula.genre.size){
        genre += pelicula.genre[i] + " | "
    }
    return genre
}