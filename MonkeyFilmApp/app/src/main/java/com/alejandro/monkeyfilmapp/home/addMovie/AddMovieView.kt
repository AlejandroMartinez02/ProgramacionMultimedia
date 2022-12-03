package com.alejandro.monkeyfilmapp.home.addMovie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alejandro.monkeyfilmapp.R
import com.alejandro.monkeyfilmapp.home.ui.HomeModelView
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
        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.width(80.dp)) {
            Icon(painter = painterResource(R.drawable.ic_baseline_arrow_back_24), contentDescription = "Back", tint = Color.White)
        }
        Image(
            painter = painterResource(id = R.drawable.mono_pelicula),
            contentDescription = "",
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
        Spacer(modifier = Modifier.padding(5.dp))
        GeneralField(
            value = title,
            onValueChange = { modelView.changeMovie(it, desc, genre) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            "Título"
        )
        Spacer(modifier = Modifier.padding(5.dp))
        GeneralField(
            value = genre,
            onValueChange = { modelView.changeMovie(title, desc, it) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            "Géneros"
        )
        Spacer(modifier = Modifier.padding(5.dp))
        GeneralField(
            value = desc,
            onValueChange = { modelView.changeMovie(title, it, genre) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            "Descripcion"
        )
        Button(onClick = {
            modelView.addMovie()
            navController.popBackStack()

        }, enabled = enabled, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(48.dp)
            .border(2.dp, Color.White),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF202B5D),
                disabledBackgroundColor = Color(0xFF5F5F9C),
                contentColor = Color.White
            )) {
            Text("Añadir película")
        }
    }
}

@Composable
fun GeneralField(value: String, onValueChange: (String) -> Unit, modifier: Modifier, label : String) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        label = { Text(label) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        )
    )
}
