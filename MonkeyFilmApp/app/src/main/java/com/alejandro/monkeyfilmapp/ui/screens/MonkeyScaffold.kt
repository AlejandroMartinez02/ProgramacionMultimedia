package com.alejandro.monkeyfilmapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.MovieModel
import com.alejandro.monkeyfilmapp.home.HomeModelView
import com.alejandro.monkeyfilmapp.ui.theme.azulFondo

@Composable
fun MonkeyTopBar(){
    TopAppBar(
        title = {
            Text(text = "Pelis")
        },
        backgroundColor = azulFondo,
        contentColor = Color.White,
        elevation = 123.dp,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.List, contentDescription = "back")
            }
        },
        actions = {

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "search")
            }
        }
    )
}

@Composable
fun MonkeyBottomBar(modelView: HomeModelView, navigationController: NavHostController) {
    val selected by modelView.showFavourites.observeAsState(false)
    BottomNavigation(
        backgroundColor = azulFondo,
        contentColor = Color.White,
    ) {
        BottomNavigationItem(selected = !selected!!, onClick = {
            if(selected){
                modelView.changeFavourites(false)
                navigationController.popBackStack()
            }

        }, icon = {

            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home"
            )
        })
        BottomNavigationItem(selected = selected!!, onClick = {
            if(!selected){
                modelView.changeFavourites(true)
                navigationController.navigate(Routes.Home.route)
            }

        }
            , icon = {

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favourites"
            )
        })
    }
}

@Composable
fun MovieCards(viewModel: HomeModelView) {
    val peliculas = viewModel.peliculas.observeAsState()
    LazyColumn(modifier = Modifier.background(Color(0xFF5454B8))) {
        item {
            if(viewModel.showFavourites.value == true){
                peliculas.value?.forEach { pelicula ->
                    if(pelicula.favorite){
                        MyCard(pelicula)
                        Spacer(modifier = Modifier
                            .padding(bottom = 2.dp)
                            .background(Color(0xFF5454B8)))
                    }
                }
            }else{
                peliculas.value?.forEach{pelicula ->
                    MyCard(pelicula)
                    Spacer(modifier = Modifier
                        .padding(bottom = 2.dp)
                        .background(Color(0xFF5454B8)))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCard(pelicula: MovieModel) {
    var expandedState by rememberSaveable { mutableStateOf(false) }

    Card(elevation = 10.dp,modifier = Modifier
        .fillMaxWidth()
        .height(70.dp),
        onClick = {expandedState = !expandedState})
    {
        Row(Modifier.padding(start = 1.dp, top = 4.dp)){
            /* Image(
                 painter = painterResource(id = pelicula.cartel),
                 contentDescription = "",
                 modifier = Modifier
                     .size(60.dp)
             )*/
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Column(Modifier.padding(start = 20.dp, top = 7.dp)) {
                    Text(pelicula.title)
                    Row{
                        Icon(
                            imageVector = Icons.Default.Star, contentDescription = "Puntuacion",
                            Modifier
                                .size(20.dp)
                                .padding(top = 1.dp),
                            tint = Color.Yellow
                        )
                        Text(text = pelicula.score.toString(), fontSize = 12.sp, modifier = Modifier.padding(top = 3.dp))
                    }
                }
                IconButton(
                    onClick = {pelicula.favorite = !pelicula.favorite},
                    modifier = Modifier.padding(top = 7.dp)
                ) {
                    Image(imageVector = Icons.Default.Star, contentDescription = "")
                }
            }
        }

    }
    if(expandedState){
        Text(
            text = pelicula.description,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            modifier = Modifier.padding(7.dp)
        )
        /*Image(
            painterResource(pelicula.cartel), contentDescription = "",
            Modifier
                .height(200.dp)
                .fillMaxWidth())*/
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MonkeyMainScaffold(
    modelView: HomeModelView,
    navigationController: NavHostController
) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MonkeyTopBar() },
        scaffoldState = scaffoldState,
        bottomBar = { MonkeyBottomBar(modelView,navigationController) },
        content = { MovieCards(modelView)}
    )
}

