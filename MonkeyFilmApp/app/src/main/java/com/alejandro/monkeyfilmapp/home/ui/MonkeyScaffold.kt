package com.alejandro.monkeyfilmapp.home.ui

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.home.data.MovieModel
import com.alejandro.monkeyfilmapp.R
import com.alejandro.monkeyfilmapp.ui.navigation.Routes
import com.alejandro.monkeyfilmapp.ui.theme.*

@Composable
fun MonkeyTopBar(){
    TopAppBar(
        title = {
            Text(text = " Peliculas")
        },
        backgroundColor = azulFondo,
        contentColor = Color.White,
        elevation = 123.dp
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
                navigationController.popBackStack()
                modelView.changeFavourites(false)

            }else{
               refreshView(navigationController)
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
            }else{
                refreshView(navigationController)
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
fun MovieCards(viewModel: HomeModelView, navigationController: NavHostController) {
    val peliculas = viewModel.peliculas.observeAsState()
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(azulCards2, azulCards1)
                )
            )
            .fillMaxSize()) {
        item {
            if(viewModel.showFavourites.value == true){
                peliculas.value?.forEach{ pelicula ->
                    if(pelicula.favorite){
                        MyCard(pelicula, navigationController)
                    }
                }
            }else{
                Log.d("ddfg", "${peliculas.value?.size}")
                peliculas.value?.forEach{pelicula ->
                    MyCard(pelicula, navigationController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCard(pelicula: MovieModel, navigationController: NavHostController) {
    var expandedState by rememberSaveable { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(bottom = 5.dp)
        .background(
            brush = Brush.horizontalGradient(
                listOf(azulCards2, azulCards1)
            )
        ),
        onClick = {expandedState = !expandedState})
    {
        Row(Modifier.background(brush = Brush.horizontalGradient(
            listOf(azulCards2, azulCards1)
        ),
        ), verticalAlignment = Alignment.CenterVertically){
            Image(
                 painter = painterResource(obtenerFoto(pelicula.cartel)),
                 contentDescription = "",
                contentScale = ContentScale.FillHeight,
                 modifier = Modifier
                     .height(170.dp)
                     .padding(5.dp)
             )
            if(expandedState){
                Column(verticalArrangement = Arrangement.SpaceAround, ) {
                    Text(
                        text = pelicula.description,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 4,
                        modifier = Modifier
                            .padding(7.dp)
                            .align(Alignment.CenterHorizontally),
                        fontFamily = Calibri,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                    Row(horizontalArrangement = Arrangement.SpaceAround,  modifier = Modifier.fillMaxWidth()){
                        IconButton(onClick = {
                            navigationController.navigate(Routes.ExpandMovie.createRoute(pelicula.id))
                        }) {
                            Icon(painter = painterResource(id = R.drawable.expand_movie), contentDescription = "Expand movie", tint = Color.White)
                        }

                        IconButton(onClick = {/*FUTURA FUNCION*/}) {
                            Icon(painter = painterResource(id = R.drawable.modify_movie), contentDescription = "Modify Movie", tint = Color.White)
                        }
                        IconButton(
                            onClick =
                            {
                                pelicula.favorite = !pelicula.favorite
                                refreshView(navigationController)
                            }
                        ) {
                            val image = if(pelicula.favorite){
                                R.drawable.favourite
                            }else
                                R.drawable.no_favourite
                            Icon(painter = painterResource(id = image), contentDescription = "Fav", tint = Color.White)
                        }
                    }
                }
            }else{

            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ){

                Text(pelicula.title, color = Color.White, fontFamily = Calibri, fontSize = 22.sp, modifier= Modifier.padding(start = 4.dp))
                Row{
                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "Puntuacion",
                        Modifier
                            .size(30.dp)
                            .padding(top = 1.dp),
                        tint = Color.Yellow
                    )
                    Text(text = pelicula.score.toString(), fontSize = 17.sp, fontFamily = Calibri, color = Color.White,modifier = Modifier.padding(top = 6.dp))
                }
            }
        }
        }

    }

}

fun refreshView(navigationController: NavHostController) {
    val idView = navigationController.currentDestination?.id
    navigationController.navigate(idView!!)
    navigationController.popBackStack(idView, inclusive = true)

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
        floatingActionButton = { MonkeyButton(navigationController) },
        content = { MovieCards(modelView, navigationController) }
    )
}

@Composable
fun MonkeyButton(navigationController: NavHostController){
    FloatingActionButton(onClick = {navigationController.navigate(Routes.AddMovie.route)}, backgroundColor = Color.White) {
        Icon(painter = painterResource(id = R.drawable.expand_movie) , contentDescription = "Add movie", tint = azulFondo)
    }
}

fun obtenerFoto(id : Int) : Int{
    var imagen = R.drawable.ic_baseline_error_outline_24
    when(id){
        1 -> imagen = R.drawable.c1
        2 -> imagen = R.drawable.c2
        3-> imagen = R.drawable.c3
        4-> imagen = R.drawable.c4
        5-> imagen = R.drawable.c5
        6-> imagen = R.drawable.c6
        7-> imagen = R.drawable.c7
        8-> imagen = R.drawable.c8
        9-> imagen = R.drawable.c9
        10-> imagen = R.drawable.c10
        0 -> imagen = R.drawable.ic_launcher_background
    }
    return imagen
}


/*
ORDENAR POR PARAMETRO
fun ordenar(lista : ArrayList<MovieModel>){
    val newArray = lista
    newArray.sortBy { it.score}
    Log.d("", newArray.toString())
}
*/
