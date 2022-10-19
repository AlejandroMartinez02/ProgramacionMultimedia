package com.alejandro.estadobotonesimagenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandro.estadobotonesimagenes.ui.theme.EstadoBotonesImagenesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstadoBotonesImagenesTheme {
                Column(modifier = Modifier
                    .padding(horizontal = 40.dp)){
                    ImagenYBoton()
                    CambioTexto()
                }
            }
        }
    }
}

@Composable
@Preview
fun ImagenYBoton(){
    var edicion by rememberSaveable { mutableStateOf(false) }
    val img = if(edicion){
        painterResource(R.drawable.carretera)
    }else{
        painterResource(R.drawable.carretera2)
    }
   Column(modifier = Modifier.padding(top = 20.dp)){
       IconButton(onClick = {edicion = !edicion},
       modifier = Modifier
           .clip(CircleShape)
           .align(Alignment.End)
           .background(Color.Cyan)
           .border(BorderStroke(2.dp, Color.Blue), CircleShape)
           ){
           Icon(
               painter= painterResource(R.drawable.lapiz),
               contentDescription = "editar"
           )
       }
       Image(painter = img, contentDescription = "Carretera", modifier = Modifier
           .fillMaxWidth()
           .height(250.dp))
   }
}


@Preview
@Composable
fun CambioTexto() {
    var nombre by rememberSaveable { mutableStateOf("")}
    var informacion by rememberSaveable { mutableStateOf("")}

    var nombreText by rememberSaveable { mutableStateOf("Hola Mundo") }
    var infoText by rememberSaveable { mutableStateOf("La historia de un programador") }

    var edicion by rememberSaveable { mutableStateOf(false) }
    var likes by rememberSaveable { mutableStateOf((0..200).random().toString()) }

    val img = if(edicion){
        painterResource(R.drawable.corazon)
    }else{
        painterResource(R.drawable.corazonroto)
    }

    Column {
        Row(Modifier.padding(bottom = 100.dp)) {
            Image(painter = painterResource(id = R.drawable.perfilmujer),
                contentDescription = "Foto de perfil",
                Modifier
                    .size(40.dp)
                    .clip(CircleShape))
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(text = nombreText)
                Text(text = infoText,
                    fontSize = 10.sp
                )
            }
            IconButton(onClick = {
                edicion = !edicion
                likes = if(edicion)
                    (likes.toInt() + 1).toString()
                else{
                    (likes.toInt() - 1).toString()
                }
            },
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(start = 0.dp)
            ){
                Icon(
                    painter= img,
                    contentDescription = "editar"
                )

            }
            Text(likes, Modifier.padding(top = 12.dp))

        }

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Introduce nombre") },
            maxLines = 1,
            singleLine = true
        )
        var info = OutlinedTextField(
            value = informacion,
            onValueChange = { informacion = it },
            label = { Text("Introduce informacion") },
            maxLines = 2,
        )

        Button(onClick = {
            nombreText = nombre
            infoText = informacion
            nombre = ""
            informacion = ""
        },
        Modifier.align(Alignment.End),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Green,
            contentColor = Color.White)){
            Text("Actualizar")
        }
    }
}
