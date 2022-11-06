package es.amm.examenalejandromartinez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.Visibility
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var vista by rememberSaveable { mutableStateOf(0) }
            val cards by rememberSaveable { mutableStateOf(mutableListOf<Pair<String,Int>>(Pair("Peli 1", 6),
                Pair("Peli 2", 8),
                Pair("Peli 3", 3)))}
            val pelisFavs by rememberSaveable{ mutableStateOf(mutableListOf<Pair<String,Int>>(Pair("Peli 1", 6))) }

            if (vista == 0) {
                MyDialogSignIn(signInChange = {vista = it})
            } else if(vista == 1){
                MyHome(
                    cards = cards ,
                    onChangeFavouriteList = {if(!pelisFavs.contains(it)){
                        pelisFavs.add(Pair(it.first, it.second))
                        }},
                    onChangeView = {vista = it},
                    listCompare = pelisFavs,
                    addMovies = {cards.add(it)}
                )
            }else if(vista == 2){
                MyFavourites(favourites = pelisFavs, onChangeFavouriteList = {
                     pelisFavs.remove(it)
                }, onChangeView = {vista = it}, listCompare = pelisFavs, addMovies = {cards.add(it)})
            }else{
                MyRegistro(vistaOnChange = {vista = it})
            }
        }
    }

}

@Composable
fun MyFavourites(favourites : MutableList<Pair<String,Int>>, listCompare : List<Pair<String, Int>>, onChangeFavouriteList: (Pair<String,Int>) -> Unit, onChangeView: (Int) -> Unit, addMovies : (Pair<String, Int>) -> Unit) {
    MyMainScaffold(content = favourites, listCompare = listCompare, onChangeFavouriteList, onChangeView, addMovies)
}


@Composable
fun MyHome(cards : MutableList<Pair<String,Int>>, listCompare : List<Pair<String, Int>>, onChangeFavouriteList: (Pair<String,Int>) -> Unit, onChangeView: (Int) -> Unit, addMovies : (Pair<String, Int>) -> Unit) {
    MyMainScaffold(content = cards,
    onChangeFavourite = onChangeFavouriteList, listCompare = listCompare, onChangeView = onChangeView,
        addMovies = addMovies
    )
}

@Composable
fun MyRegistro(vistaOnChange : (Int) -> Unit){
    var nombre by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var rPassword by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var deporte by rememberSaveable { mutableStateOf(false)}
    var cine by rememberSaveable { mutableStateOf(false)}
    var viaje by rememberSaveable { mutableStateOf(false)}
    var museo by rememberSaveable { mutableStateOf(false)}
    var juego by rememberSaveable { mutableStateOf(false)}
    var anime by rememberSaveable { mutableStateOf(false)}
    var error by rememberSaveable { mutableStateOf(false)}
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 20.dp)){
        Registro(
            nombre = nombre,
            password = password,
            repeatPassword = rPassword,
            email = email,
            onNameChange = {nombre = it},
            onPasswordChange = {password = it},
            onRPasswordChange = {rPassword = it},
            onEmailChange = {email = it}
        )
        Intereses(
            listaChecks = listOf(deporte,cine,viaje,museo,juego,anime),
            onDeporteChange = {deporte = !deporte},
            onCineChange = {cine = !cine},
            onViajeChange = {viaje = !viaje},
            onMuseoChange = {museo = !museo},
            onJuegoChange = {juego = !juego},
            onAnimeChange = {anime = !anime}
        )
        Button(onClick = {
              if(nombre.isNotBlank() && password == rPassword && email.contains("@")){
                  vistaOnChange(1)
              }else{
                  error = true
              }
        },
            Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Registrarse", fontSize = 20.sp)
        }
        if(error)
            Text(text = "Algún campo es incorrecto, inténtelo de nuevo.", color = Color.Red, fontSize = 10.sp, textDecoration = TextDecoration.Underline)
    }
}

@Composable
fun MyDialogSignIn(signInChange : (Int)-> Unit) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var usuario by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(scaffoldState = scaffoldState
    ) {
        Dialog(onDismissRequest ={},
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ){
                Spacer(Modifier.padding(top=20.dp))
                TextField(value = usuario,
                    onValueChange = { usuario = it },
                    placeholder = {Text(text = "Usuario")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "Usuario")
                    })
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextField(value = password,
                    onValueChange = { password = it },
                    placeholder = {Text(text = "Contraseña")},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Add
                    else Icons.Filled.Clear
                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, "")
                    }
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Contraseña")
                })
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Button(onClick = {
                    if (usuario.isNotEmpty() && password.isNotEmpty()){
                        signInChange(1)
                    }
                    else{
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "El usuario o contraseña es incorrecto",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                    Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Iniciar sesión")
                }
                Button(onClick = {signInChange(3)},
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.Blue
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    elevation = null) {
                    Text(text = "¿No estás registrado?",
                        textDecoration = TextDecoration.Underline)
                }
            }
        }
    }
    }

@Composable
fun MyCard(name:String, punt: Int, onChangeFavourite : (Pair<String,Int>) -> Unit, icono : ImageVector, scaffoldState : ScaffoldState){
    val scope = rememberCoroutineScope()
    val textoSnackBar = comprobarIcono(icono)

    Card(elevation = 10.dp,modifier = Modifier
        .fillMaxWidth()
        .height(70.dp))
    {
        Row(Modifier.padding(start = 1.dp, top = 4.dp)){
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        CircleShape
                    )
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Column(Modifier.padding(start = 20.dp, top = 7.dp)) {
                    Text(name)
                    Row{
                        Icon(
                            imageVector = Icons.Filled.Star, contentDescription = "Puntuacion",
                            Modifier
                                .size(20.dp)
                                .padding(top = 1.dp)
                        )
                        Text(text = punt.toString(), fontSize = 12.sp, modifier = Modifier.padding(top = 3.dp))
                    }
                }
                IconButton(
                    onClick = {onChangeFavourite(Pair(name, punt))
                              scope.launch {
                                  scaffoldState.snackbarHostState.showSnackbar(
                                      textoSnackBar,
                                      duration = SnackbarDuration.Short
                                  )
                              }},
                    modifier = Modifier.padding(top = 7.dp)
                ) {
                    Icon(imageVector = icono, contentDescription = "add")
                }
            }
        }
    }
}

fun comprobarIcono(icono : ImageVector) : String{
    return if(icono == Icons.Filled.Clear)
         "Peli eliminada de favoritos..."
    else
        "Peli añadida a favoritos..."
}

@Composable
fun MyMainScaffold(content:  List<Pair<String, Int>>, listCompare : List<Pair<String, Int>>, onChangeFavourite: (Pair<String,Int>) -> Unit, onChangeView : (Int) -> Unit, addMovies : (Pair<String, Int>) -> Unit){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MyTopAppBar(addMovies, scaffoldState)},
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(onChangeView)},
        drawerContent = {},
        content = { MyContent(content, listCompare = listCompare, onChangeFavourite, scaffoldState)}
    )
}

@Composable
fun MyContent( listPeople: List<Pair<String, Int>>, listCompare : List<Pair<String, Int>>, OnChangeFavourite : (Pair<String,Int>) -> Unit, scaffoldState : ScaffoldState){
    val icono : ImageVector = if(listPeople == listCompare){
        Icons.Filled.Clear
    }else{
        Icons.Filled.Add
    }
    Column(Modifier.padding(start = 2.dp)) {
        listPeople.forEach { item ->
            MyCard(item.first, item.second, onChangeFavourite = OnChangeFavourite, icono, scaffoldState)
            Spacer(modifier = Modifier.padding(bottom = 2.dp))
        }
    }
}
@Composable
fun MyTopAppBar(addMovies : (Pair<String, Int>) -> Unit,  scaffoldState : ScaffoldState){
    var anyadirPeli by rememberSaveable { mutableStateOf(false) }
    var nombrePeli by rememberSaveable{ mutableStateOf("") }
    var puntPeli by rememberSaveable{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(text = "Pelis")
        },
        backgroundColor = Color.Red,
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

            IconButton(onClick = { anyadirPeli = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        }
    )
    if(anyadirPeli){
        Dialog(onDismissRequest ={anyadirPeli = false},
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ){
                Text(text = "Direcciones", fontSize = 25.sp)
                Spacer(Modifier.padding(top=20.dp))
                TextField(value = nombrePeli, onValueChange = {nombrePeli = it}, placeholder = { Text(text = "Nombre de la peli") } )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextField(value = puntPeli, onValueChange = {puntPeli = it}, placeholder = { Text(text = "Puntuacion de la peli") } )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Button(onClick = {
                    if (nombrePeli.isNotEmpty() && puntPeli.isNotEmpty()){
                        anyadirPeli = false
                        addMovies(Pair(nombrePeli,puntPeli.toInt()))
                    }else{
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Algún parámetro está en blanco, introduzca los datos correctamente",
                                duration = SnackbarDuration.Short
                            )
                        }}
                })
                {
                    Text("Enviar")
                }
            }
        }
    }
}

@Composable
fun MyBottomNavigation(onChangeView: (Int) -> Unit){
    BottomNavigation(
        backgroundColor = Color(0xFFf2443d),
        contentColor = Color.White,
    ) {
        BottomNavigationItem(selected = false, onClick = {onChangeView(1)}, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "fav"
            )
        })
        BottomNavigationItem(selected = false, onClick = {onChangeView(2)}, icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Place"
            )
        })
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person"
            )
        })
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
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp))
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
fun Registro(nombre : String,
             password : String,
             repeatPassword : String,
             email : String,
             onNameChange : (String) -> Unit,
             onPasswordChange : (String) -> Unit,
             onRPasswordChange : (String) -> Unit,
             onEmailChange : (String) -> Unit)
{
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth()) {
        TextField(
            value = nombre,
            onValueChange = onNameChange,
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .padding(bottom = 5.dp)
        )

        TextField(value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        TextField(value = password,
            onValueChange = { onPasswordChange(it)},
            placeholder = {Text(text = "Contraseña")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Add
                else Icons.Filled.Clear
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, "")
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Contraseña")
            })
        TextField(value = repeatPassword,
            onValueChange = {onRPasswordChange(it)},
            placeholder = {Text(text = "Contraseña")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Add
                else Icons.Filled.Clear
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, "")
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Contraseña")
            })
    }
}
