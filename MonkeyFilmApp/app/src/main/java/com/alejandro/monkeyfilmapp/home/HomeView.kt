package com.alejandro.monkeyfilmapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.MediaModel
import com.alejandro.monkeyfilmapp.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeModelView: HomeModelView, navigationController: NavHostController) {
    val peliculas = homeModelView.peliculas.observeAsState()

    MovieCards(peliculas)
}


@Composable
fun MovieCards(peliculas: State<MutableList<MediaModel>?>) {
    var expandedState by rememberSaveable { mutableStateOf(false) }

    LazyColumn() {
        item {
            peliculas.value?.forEach { pelicula ->
                MyCard(pelicula)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCard(pelicula: MediaModel) {
    var expandedState by rememberSaveable { mutableStateOf(false) }

    Card(elevation = 10.dp,modifier = Modifier
        .fillMaxWidth()
        .height(70.dp),
        onClick = {expandedState = !expandedState})
    {
        Row(Modifier.padding(start = 1.dp, top = 4.dp)){
            Image(
                painter = painterResource(id = pelicula.cartel),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Column(Modifier.padding(start = 20.dp, top = 7.dp)) {
                    Text(pelicula.title)
                    Row{
                        Icon(
                            imageVector = Icons.Filled.Star, contentDescription = "Puntuacion",
                            Modifier
                                .size(20.dp)
                                .padding(top = 1.dp),
                            tint = Color.Yellow
                        )
                        Text(text = pelicula.score.toString(), fontSize = 12.sp, modifier = Modifier.padding(top = 3.dp))
                    }
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(top = 7.dp)
                ) {
                    Image(imageVector = Icons.Filled.Star, contentDescription = "")
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
        Image(
            painterResource(pelicula.cartel), contentDescription = "",
            Modifier
                .height(200.dp)
                .fillMaxWidth())
    }

}



