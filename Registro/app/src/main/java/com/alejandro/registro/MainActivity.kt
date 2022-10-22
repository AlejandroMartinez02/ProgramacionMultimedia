package com.alejandro.registro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.alejandro.registro.ui.theme.RegistroTheme
import com.alejandro.registro.ui.theme.RojoClaro
import com.alejandro.registro.ui.theme.RojoVino
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroTheme {
                Column(Modifier.fillMaxSize()){
                    Registro()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Registro() {
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    var telefono by rememberSaveable { mutableStateOf("") }
    var contactos by rememberSaveable { mutableStateOf(false) }
    var visible by rememberSaveable { mutableStateOf(true) }
    var telefonoCB by rememberSaveable { mutableStateOf(false)}

    var deporte by rememberSaveable { mutableStateOf(false)}
    var cine by rememberSaveable { mutableStateOf(false)}
    var viaje by rememberSaveable { mutableStateOf(false)}
    var museo by rememberSaveable { mutableStateOf(false)}
    var juego by rememberSaveable { mutableStateOf(true)}
    var anime by rememberSaveable { mutableStateOf(false)}
    //RadioButton
    val estudios = listOf("No", "Secundarios", "Superiores")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(estudios[1] ) }
    //Enviar
    var showloading by rememberSaveable { mutableStateOf(false) }
    var progress by rememberSaveable { mutableStateOf(0F) }

    Column {
        Text(
            text = "Registro",
            fontSize = 30.sp,
            color = RojoVino,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
        )
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
        )

        TextField(value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
        )
        TextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)){
            Column{
                Text("Añadir contactos")
                Switch(checked = contactos,
                    onCheckedChange = {contactos = !contactos}
                )
            }
            Column{
                Text("Visible")
                Switch(checked = visible,
                    onCheckedChange = {visible = !visible}
                )
            }
            Column {
                Text("Telefono")
                Switch(checked = telefonoCB,
                    onCheckedChange = {telefonoCB = !telefonoCB}
                )
            }
        }
        TextField(value = telefono,
            label = { Text("Telefono") },
            onValueChange = {
                telefono = if(it.contains(Regex("[a-zA-Z]"))){
                    it.replace(Regex("[a-zA-Z]"), "")
                }else{
                    it
                }
                },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
                .background(RojoClaro)

        )
        Text(text = "Intereses",
        Modifier.padding(10.dp))
        Row{
            Column{
                Row{
                    Checkbox(checked = deporte, onCheckedChange = {deporte = !deporte})
                    Text("Deportes", Modifier.padding(top=15.dp))
                }
                Row{
                    Checkbox(checked = cine, onCheckedChange = {cine = !cine})
                    Text("Cine", Modifier.padding(top=15.dp))
                }
                Row{
                    Checkbox(checked = viaje, onCheckedChange = {viaje = !viaje})
                    Text("Viajes", Modifier.padding(top=15.dp))
                }
            }
            Spacer(Modifier.width(30.dp))
            Column{
                Row{
                    Checkbox(checked = museo, onCheckedChange = {museo = !museo})
                    Text("Museos", Modifier.padding(top=15.dp))
                }
                Row{
                    Checkbox(checked = juego, onCheckedChange = {juego = !juego})
                    Text("Videojuegos", Modifier.padding(top=15.dp))
                }
                Row{
                    Checkbox(checked = anime, onCheckedChange = {anime = !anime})
                    Text("Anime", Modifier.padding(top=15.dp))
                }
            }
        }
        Text("Estudios",
        Modifier.padding(15.dp))
        Row{
            estudios.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(horizontal = 10.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(top=15.dp)
                    )
                }
            }
        }
        Button(onClick = {showloading = true},
            Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = RojoClaro
        )) {
            Text("Enviar",
            color = Color.White)
        }
        if(showloading){
            LaunchedEffect(true) { // Incrementos

                for (i in 0..100 step 10) {
                    delay(400)

                    if (i > 100) {
                        break
                    }
                    progress = i / 100f
                }
            }
            val animatedProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            )
            LinearProgressIndicator(color = Color.Red, progress = animatedProgress, modifier = Modifier.fillMaxWidth().padding(horizontal = 100.dp).height(10.dp))
            Text("La información ha sido enviada",
            modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}