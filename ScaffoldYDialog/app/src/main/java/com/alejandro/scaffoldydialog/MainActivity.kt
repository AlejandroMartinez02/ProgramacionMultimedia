package com.alejandro.scaffoldydialog

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var show by remember{ mutableStateOf(false) }
            var showConfirmDialog by remember{ mutableStateOf(false) }
            var showSnackbar by remember{ mutableStateOf(false) }
            val listPeople by remember{ mutableStateOf(mutableListOf(
                Pair("James Richardson", "San Francisco, CA"),
                Pair("Madeline Kennedy", "Fremont, CA"),
                Pair("Anna Coleman", "San Francisco, CA")
            ))}
            var name by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            MyScaffold(listPeople = listPeople,
                onDialogChange =
            { show = !show
            },
                onChangeSnackbar = {showSnackbar = it},
                showSnackbar = showSnackbar,
                onChangeConfirmDialog = {showConfirmDialog = !showConfirmDialog}
            )
            MySimpleCustomDialog(
                show = show,
                showChange = { show = !show},
                firstName = name,
                firstNameChange = {name = it},
                lastName = lastName,
                lastNameChange = {lastName = it},
                {listPeople.add(Pair(name,lastName))}
            )
            MyNavigation(showDialog = showConfirmDialog,
                dialogChange = { showConfirmDialog = !showConfirmDialog},
                snackBarChange = {showSnackbar = it})
        }
    }
}
@Composable
fun MyScaffold(listPeople:  List<Pair<String, String>>, onDialogChange: ()-> Unit, onChangeSnackbar : (Boolean) -> Unit, showSnackbar : Boolean, onChangeConfirmDialog : () -> Unit){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MyTopAppBar()},
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(showSnackbar, onChangeSnackbar)},
        floatingActionButton = { MyFAB(onDialogChange) },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        drawerContent = { MyDrawer()},
        content = { MyContent(listPeople, onChangeConfirmDialog)}
    )
}

@Composable
fun MySimpleCustomDialog(show: Boolean, showChange:() -> Unit,firstName: String, firstNameChange: (String) -> Unit, lastName: String, lastNameChange: (String) -> Unit, addPeople : () -> Unit){
    if (show){
        Dialog(onDismissRequest ={showChange()},
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
                MyDialogTextFields(texto = "Nombre",name = firstName,  onValueChange = firstNameChange)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                MyDialogTextFields(texto = "Direccion",name = lastName, onValueChange = lastNameChange)
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Button(onClick = {
                    if (firstName.isNotEmpty() && lastName.isNotEmpty()){
                        showChange()
                        addPeople()
                    }
                }) {
                    Text("Enviar")
                }
            }
        }
    }
}

@Composable
fun MyDialogTextFields(texto: String, name: String,  onValueChange: (String) -> Unit){
    Column() {
        TextField(value = name, onValueChange = onValueChange, placeholder = { Text(text = texto) } )
    }
}

@Composable
fun MyCard(name:String, location:String, onChangeSnackbar : () -> Unit){
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
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Column(Modifier.padding(start = 20.dp, top = 7.dp)) {
                    Text(name)
                    Row {
                        Icon(
                            imageVector = Icons.Filled.Place, contentDescription = "place",
                            Modifier
                                .size(20.dp)
                                .padding(top = 1.dp)
                        )
                        Text(text = location, fontSize = 12.sp, modifier = Modifier.padding(top = 3.dp))
                    }
                }
                IconButton(
                    onClick = {
                        onChangeSnackbar()
                    },
                    Modifier.padding(top = 7.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                }

            }
        }
    }
}

@Composable
fun MyContent( listPeople: List<Pair<String, String>>, onChangeSnackbar : () -> Unit){
    Column(Modifier.padding(start = 2.dp)) {
        listPeople.forEach{ item ->
            MyCard(item.first, item.second, onChangeSnackbar)
            Spacer(modifier = Modifier.padding(bottom = 2.dp))
        }

    }
}

@Composable
fun MyDrawer() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Gray)) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .background(Color.Red)
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 100.dp, top=35.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier
                    .size(60.dp)
                    .clip(
                        CircleShape
                    ))
                Text(text = "Dave Fluid", fontSize = 20.sp)
                Text(text = "San Francisco, CA" )//, modifier = Modifier.padding(start = 115.dp))
            }
        }
        Spacer(Modifier.padding(top = 100.dp))

        Column(modifier = Modifier
            .background(Color.White)) {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
              Text(text = "Education",
                  fontSize = 15.sp
                  ,modifier = Modifier
                  .padding(vertical = 20.dp, horizontal = 10.dp))
              Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription ="", Modifier.size(25.dp) )
          }
            Divider()
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Text(text = "Experience",
                    fontSize = 15.sp,
                    modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 10.dp))
                Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription ="", Modifier.size(25.dp) )
            }
            Divider()
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
               Text(text = "Skills",
                   fontSize = 15.sp,
                   modifier = Modifier
                   .padding(vertical = 20.dp, horizontal = 10.dp))
               Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription ="", Modifier.size(25.dp) )
           }
        }

    }
}

@Composable
fun MyFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyTopAppBar(){
    TopAppBar(
        title = {
            Text(text = "Alan Smith")
        },
        backgroundColor = Color(0xFF17b9e3),
        contentColor = Color.White,
        elevation = 123.dp,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
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
fun MyBottomNavigation(snackbar : Boolean, onChangeSnackbar: (Boolean) -> Unit){
    BottomNavigation(
        backgroundColor = Color(0xFFf2443d),
        contentColor = Color.White,
    ) {
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "refresh"
            )
        })
        BottomNavigationItem(selected = false, onClick = {  }, icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "fav"
            )
        })
        BottomNavigationItem(selected = false, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Place"
            )
        })
        BottomNavigationItem(selected = false, onClick = {  }, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person"
            )
        })
        if (snackbar) {
            Snackbar(backgroundColor = Color.Red, ) {
                Column(Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly){
                   Row(Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween){
                       Text("Iniciando ruta...", fontSize = 18.sp, modifier =Modifier.padding(10.dp))
                       Button(onClick = {onChangeSnackbar(false)}) {
                           Text(text = "Cancelar")}
                    }
                }
            }
        }
    }
}

@Composable
fun MyNavigation(showDialog : Boolean, dialogChange : () -> Unit, snackBarChange : (Boolean) -> Unit){
    if(showDialog) {
        Dialog(
            onDismissRequest = { dialogChange() },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Box(
                Modifier
                    .background(Color.White)
                    .padding(13.dp)
                    .fillMaxWidth()) {
                Column(Modifier.
                        fillMaxWidth()
                    ) {
                    Text(
                        "¿Desea iniciar la ruta?",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 20.dp)
                    )
                    Row(horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {dialogChange()},
                            Modifier
                                .height(27.dp)
                                .width(82.dp)) {
                            Text("Cancelar", fontSize = 12.sp)
                        }
                        Button(onClick = { dialogChange()
                            snackBarChange(true) },
                            Modifier
                                .height(27.dp)
                                .width(82.dp)) {
                            Text("Iniciar", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
