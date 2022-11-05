package es.amm.examenalejandromartinez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.amm.examenalejandromartinez.ui.theme.ExamenAlejandroMartinezTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var vista by rememberSaveable { mutableStateOf(0) }
            var nombrePeli by rememberSaveable { mutableStateOf("") }
            var puntPeli by rememberSaveable { mutableStateOf(0) }
            val cards by rememberSaveable { mutableStateOf(mutableListOf<Pair<String,Int>>(Pair("Peli 1", 6),
                Pair("Peli 2", 8),
                Pair("Peli 3", 3)))}
            var pelisFavs by rememberSaveable{ mutableStateOf(mutableListOf<Pair<String,Int>>(Pair("Peli 1", 6))) }

            if (vista == 0) {
                MyDialogSignIn(signInChange = {vista = it})
            } else if(vista == 1){
                MyHome(
                    cards = cards ,
                    onChangeFavouriteList = {pelisFavs.add(Pair(nombrePeli, puntPeli))},
                    onChangeView = {vista = it}
                )
            }else if(vista == 2){
                MyFavourites(favourites = pelisFavs, onChangeFavouriteList = {}, onChangeView = {vista = it})
            }else{
                MyRegistro()
            }


        }
    }

}

@Composable
fun MyFavourites(favourites : MutableList<Pair<String,Int>>, onChangeFavouriteList: () -> Unit, onChangeView: (Int) -> Unit) {
    MyScaffold(content = favourites, onChangeFavouriteList, onChangeView)
}


@Composable
fun MyHome(cards : MutableList<Pair<String,Int>>, onChangeFavouriteList: () -> Unit, onChangeView: (Int) -> Unit) {
    MyScaffold(content = cards,
    onChangeFavourite = onChangeFavouriteList, onChangeView)

}

@Composable
fun MyRegistro(){

}

@Composable
fun MyDialogSignIn(signInChange : (Int)-> Unit)
    {
        var usuario by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        Dialog(onDismissRequest ={signInChange(0)},
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ){
                Spacer(Modifier.padding(top=20.dp))
                MyDialogTextFields(texto = "",name = usuario,  onValueChange = {usuario = it},  placeholder = "Usuario/email")
                Spacer(modifier = Modifier.padding(top = 10.dp))
                MyDialogTextFields(texto = "",name = password, onValueChange = {password = it}, placeholder = "Contraseña")
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Button(onClick = {
                    if (usuario.isNotEmpty() && password.isNotEmpty()){
                        signInChange(1)
                    }
                },
                Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Iniciar sesión")
                }
                Button(onClick = {signInChange(3)},
                Modifier.background(Color.Transparent)) {
                    Text(text = "¿No estás registrado?")
                }
            }
        }
    }

@Composable
fun MyDialogTextFields(texto: String, name: String,  onValueChange: (String) -> Unit, placeholder : String){
    Column() {
        TextField(value = name,
            onValueChange = onValueChange,
            placeholder = {placeholder} )
    }
}

@Composable
fun MyCard(name:String, punt:String, onChangeFavourite : () -> Unit){
    Card(elevation = 10.dp,modifier = Modifier
        .fillMaxWidth()
        .height(70.dp))
    {
        Row(Modifier.padding(start = 1.dp, top = 4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        CircleShape
                    )
            )
            Column(Modifier.padding(start = 40.dp, top = 10.dp)) {
                Text(name)
                Row() {
                    Icon(
                        imageVector = Icons.Filled.Star, contentDescription = "Puntuacion",
                        Modifier
                            .size(20.dp)
                            .padding(top = 1.dp)
                    )
                    Text(text = punt, fontSize = 12.sp, modifier = Modifier.padding(top = 3.dp))
                }
            }
            Row(horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = {onChangeFavourite()},
                    modifier = Modifier.padding(start = 160.dp, top = 5.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                }
            }
        }
    }
}

@Composable
fun MyScaffold(content:  List<Pair<String, Int>>, onChangeFavourite: () -> Unit, onChangeView : (Int) -> Unit){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MyTopAppBar()},
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(onChangeView)},
        drawerContent = { MyDrawer()},
        content = { MyContent(content, onChangeFavourite)}
    )
}

@Composable
fun MyContent( listPeople: List<Pair<String, Int>>, OnChangeFavourite : () -> Unit){
    Column(Modifier.padding(start = 2.dp)) {
        listPeople.forEach{ item ->
            MyCard(item.first, item.second.toString(), onChangeFavourite = OnChangeFavourite)
            Spacer(modifier = Modifier.padding(bottom = 2.dp))
        }

    }
}

@Composable
fun MyDrawer() {
    Modifier.background(Color.Green)
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Gray)) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Red)
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 100.dp, top=35.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier
                    //.padding(start = 130.dp, top = 5.dp)
                    .size(60.dp)
                    .clip(
                        CircleShape
                    ))
            }
        }
        Spacer(Modifier.padding(top = 100.dp))

        Column(modifier = Modifier
            .background(Color.White)) {
            Text(text = "Primera opción", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 10.dp))
            Divider()
            Text(text = "Segunda opción", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 10.dp))
            Divider()
            Text(text = "Tercera opción", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 10.dp))
            Divider()
            Text(text = "Cuarta opción", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 10.dp))
        }

    }
}
@Composable
fun MyTopAppBar(){
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

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        }
    )
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
        BottomNavigationItem(selected = false, onClick = {onChangeView(3)}, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person"
            )
        })
    }
}