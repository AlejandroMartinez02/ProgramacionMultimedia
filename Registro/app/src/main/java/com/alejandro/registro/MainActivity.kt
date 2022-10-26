package com.alejandro.registro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandro.registro.ui.theme.RegistroTheme
import com.alejandro.registro.ui.theme.RojoClaro
import com.alejandro.registro.ui.theme.RojoVino

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
            var contadorCB = 0
            val estudios = listOf("No", "Secundarios", "Superiores")
            val selectedValue = remember{ mutableStateOf("") }
            var showloading by rememberSaveable { mutableStateOf(false) }
            var texto = ""
            RegistroTheme {
                Column(Modifier.fillMaxSize()){
                    Registro(nombre, apellidos, email,
                        onNameChange = {nombre = it},
                        onSurnameChange = {apellidos = it},
                        onEmailChange = {email = it})
                    MySwitches(contactos, visible, telefonoCB , telefono,
                        onContactChange = {contactos =!contactos},
                        onVisible = {visible = !visible},
                        onTlfChange ={telefonoCB = !telefonoCB} ,
                        onTflTFChange = { telefono = if(it.contains(Regex("[a-zA-Z]"))){
                            it.replace(Regex("[a-zA-Z]"), "")
                        }else{
                            it
                        }}
                    )
                    Intereses(
                        listaChecks = listOf(deporte,cine,viaje,museo,juego,anime),
                        onDeporteChange = {deporte = !deporte},
                        onCineChange = {cine = !cine},
                        onViajeChange = {viaje = !viaje},
                        onMuseoChange = {museo = !museo},
                        onJuegoChange = {juego = !juego},
                        onAnimeChange = {anime = !anime},
                    )
                    Estudios(estudios, {selectedValue.value == it}, {selectedValue.value = it})
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
                        LinearProgressIndicator(color = Color.Red, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 100.dp)
                            .height(10.dp))
                        contadorCB = contar(listOf(deporte,cine,viaje,museo,juego,anime))
                        texto = "La información ha sido enviada. Tiene $contadorCB interes"
                        if(contadorCB > 1){
                            texto +="es"
                        }
                        Text(text = texto,
                            modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }
            }
        }
    }
}


@Composable
fun Registro(nombre : String,
             apellidos : String,
             email : String,
             onNameChange : (String) -> Unit,
             onSurnameChange : (String) -> Unit,
             onEmailChange : (String) -> Unit)
{

    Column(Modifier.fillMaxWidth()) {
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
            onValueChange = onNameChange,
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
        )

        TextField(value = apellidos,
            onValueChange = onSurnameChange,
            label = { Text("Apellidos") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
        )
        TextField(value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
        )


    }
}

@Composable
fun MySwitches(contactos : Boolean,
               visible : Boolean,
               telefono : Boolean,
               telefonoNum : String,
               onContactChange : (Boolean) -> Unit,
               onVisible : (Boolean) -> Unit,
               onTlfChange : (Boolean) -> Unit,
               onTflTFChange : (String) -> Unit){
    Column{
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)){
            Column{
                Text("Añadir contactos")
                Switch(checked = contactos,
                    onCheckedChange = onContactChange
                )
            }
            Column{
                Text("Visible")
                Switch(checked = visible,
                    onCheckedChange = onVisible
                )
            }
            Column {
                Text("Telefono")
                Switch(checked = telefono,
                    onCheckedChange = onTlfChange
                )
            }
        }
        TextField(value = telefonoNum,
            onValueChange = onTflTFChange,
            label = { Text("Email") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 5.dp)
                .background(RojoClaro)

        )
    }
}

@Composable
fun Intereses(listaChecks : List<Boolean>,
              onDeporteChange : (Boolean) -> Unit,
              onCineChange : (Boolean) -> Unit,
              onViajeChange : (Boolean) -> Unit,
              onMuseoChange : (Boolean) -> Unit,
              onJuegoChange : (Boolean) -> Unit,
              onAnimeChange : (Boolean) -> Unit,
){
    Text(text = "Intereses",
        Modifier.padding(10.dp))
    Row{
        Column{
            Row{
                Checkbox(checked = listaChecks[0], onCheckedChange = onDeporteChange)
                Text("Deportes", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = listaChecks[1], onCheckedChange = onCineChange)
                Text("Cine", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = listaChecks[2], onCheckedChange = onViajeChange)
                Text("Viajes", Modifier.padding(top=15.dp))
            }
        }
        Spacer(Modifier.width(30.dp))
        Column{
            Row{
                Checkbox(checked = listaChecks[3], onCheckedChange = onMuseoChange)
                Text("Museos", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = listaChecks[4], onCheckedChange = onJuegoChange)
                Text("Videojuegos", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = listaChecks[5], onCheckedChange = onAnimeChange)
                Text("Anime", Modifier.padding(top=15.dp))
            }
        }
    }
}

@Composable
fun Estudios(estudios : List<String>,
             isSelectedItem : (String) -> Boolean,
             onChangeState : (String) -> Unit
    ){
    Text("Estudios",
        Modifier.padding(15.dp))
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ){
        estudios.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = isSelectedItem(text),
                        onClick = {
                            onChangeState(text)
                        }
                    )
                    .padding(horizontal = 10.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(text),
                    onClick = null
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}


fun contar(lista : List<Boolean>) : Int{
    var contador = 0
    for(i in lista){
        if(i)
            contador++
    }
    return contador
}