package com.alejandro.monkeyfilmapp.home.addMovie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alejandro.monkeyfilmapp.R
import com.alejandro.monkeyfilmapp.home.HomeModelView
import com.alejandro.monkeyfilmapp.ui.theme.azulFondo

@Composable
fun AddMovieView(modelView : HomeModelView, navController: NavController){
    val title by modelView.titleMovie.observeAsState("")
    val desc by modelView.description.observeAsState("")
    val genre by modelView.genreType.observeAsState("")
    val enabled by modelView.enabled.observeAsState(false)

    Column(modifier = Modifier
        .background(azulFondo)
        .fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.mono_registro), contentDescription = "", modifier = Modifier.align(
            Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(5.dp))
        GeneralField(value = title, onValueChange = {modelView.changeMovie(it, desc, genre)}, modifier = Modifier.align(Alignment.CenterHorizontally), "Título")
        Spacer(modifier = Modifier.padding(5.dp))
        GeneralField(value = genre, onValueChange = {modelView.changeMovie(title, desc, it)}, modifier = Modifier.align(Alignment.CenterHorizontally), "Géneros")
        Spacer(modifier = Modifier.padding(5.dp))
        GeneralField(value = desc, onValueChange = {modelView.changeMovie(title, it, genre)}, modifier = Modifier.align(Alignment.CenterHorizontally), "Descripcion")
        Button(onClick = {modelView.addMovie()
            navController.popBackStack()}, enabled = enabled) {
        }
    }
}

@Composable
fun GeneralField(value: String, onValueChange: (String) -> Unit, modifier: Modifier, label : String) {
    TextField(value = value, onValueChange = {onValueChange(it)}, modifier = modifier, label = {Text(label)})
}
