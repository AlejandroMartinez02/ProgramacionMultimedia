package com.alejandro.monkeyfilmapp.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.R
import com.alejandro.monkeyfilmapp.ui.theme.*


@Composable
fun RegisterScreen(modelView: RegisterModelView, navController: NavHostController){
    val email by modelView.email.observeAsState("")
    val user by modelView.user.observeAsState("")
    val password by modelView.password.observeAsState("")
    val confirmPassword by modelView.confirmPassword.observeAsState("")
    val isLoading by modelView.isLoading.observeAsState(false)
    val enabledButton by modelView.isButtonLoginEnable.observeAsState(false)

    Column(
        Modifier
            .fillMaxSize()
            .background(azulFondo)
            .padding(10.dp)) {
        Image(
            painter = painterResource(id = R.drawable.mono_registro),
            contentDescription = "Mono registro",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(200.dp)
                .width(200.dp)
        )
        UserField(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(), user = user) {
            modelView.validRegister(
                it,
                password,
                confirmPassword,
                email
            )
        }
        Spacer(Modifier.padding(8.dp))
        EmailField(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            email = email,
            emailChange = { modelView.validRegister(user, password, confirmPassword, it) })
        Spacer(Modifier.padding(8.dp))
        PasswordFields(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            password,
            confirmPassword,
            { modelView.validRegister(user, it, confirmPassword, email) }) {
            modelView.validRegister(user, password, it, email)
        }
        Spacer(Modifier.padding(8.dp))
        Box(Modifier.fillMaxWidth()){
            CheckBoxes(modelView)
        }
        ButtonRegister(enabledButton){modelView.registerButtonPress(navController)}
        if(isLoading){
            LinearProgressIndicator(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp), color = Color.White)
        }
    }
}

@Composable
fun CheckBoxes(registerModelView: RegisterModelView) {
    val accion by registerModelView.accion.observeAsState(false)
    val crimen by registerModelView.crimen.observeAsState(false)
    val drama by registerModelView.drama.observeAsState(false)
    val aventura by registerModelView.aventura.observeAsState(false)
    val fantasia by registerModelView.fantasia.observeAsState(false)
    val sifi by registerModelView.sifi.observeAsState(false)


    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
        Column{
            Row{
                Checkbox(checked = accion, onCheckedChange = {registerModelView.changeAccion(!accion)})
                Text("Accion", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = crimen, onCheckedChange = {registerModelView.changeCrimen(!crimen)})
                Text("Crimen", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = drama, onCheckedChange = {registerModelView.changeDrama(!drama)})
                Text("Drama", Modifier.padding(top=15.dp))
            }
        }
        Column{
            Row{
                Checkbox(checked = aventura, onCheckedChange = {registerModelView.changeAventura(!aventura)})
                Text("Aventura", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked =fantasia, onCheckedChange = {registerModelView.changeFantasia(!fantasia)})
                Text("Fantasia", Modifier.padding(top=15.dp))
            }
            Row{
                Checkbox(checked = sifi, onCheckedChange =  {registerModelView.changeSifi(!sifi)})
                Text("Si-Fi", Modifier.padding(top=15.dp))
            }
        }
    }
}

@Composable
fun ButtonRegister(enabledButton : Boolean, onButtonSelected : () -> Unit) {
    Button(onClick = { onButtonSelected() }, modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .border(2.dp, Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xF4444FAF),
            disabledBackgroundColor = Color(0xFF8888DF),
            contentColor = Color.White
        ),
        enabled = enabledButton) {
        Text(text = "Crear cuenta", color = Color.White)
    }
}

@Composable
fun UserField(modifier: Modifier, user: String, userChange: (String) -> Unit){
    TextField(
        value = user,
        onValueChange = { userChange(it)},
        label = { Text(text = "Usuario",color = Color.Black) },
        modifier = modifier.padding(top = 8.dp),
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        )
    )
}

@Composable
fun EmailField(modifier: Modifier, email : String, emailChange : (String) ->Unit){
    TextField(value = email,
        onValueChange = {emailChange(it)},
        label = { Text("Email", color = Color.Black) },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        )
    )
}

@Composable
fun PasswordFields(modifier: Modifier, password : String, confirmPassword : String, passwordChange : (String) -> Unit, repeatPasswordChange : (String) -> Unit){
    var passwordVisible by rememberSaveable{ mutableStateOf(false) }

    TextField(value = password,
        onValueChange = { passwordChange(it)},
        label = { Text("Contraseña", color = Color.Black) },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                R.drawable.password_visible
            else R.drawable.password_off
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(painter = painterResource(id = image), "",
                    Modifier
                        .height(20.dp)
                        .width(20.dp))
            }
        })
    Spacer(Modifier.padding(8.dp))
    TextField(value = confirmPassword,
        onValueChange = {repeatPasswordChange(it)},
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        ),
        label = { Text("Repite la contraseña", color = Color.Black) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                R.drawable.password_visible
            else R.drawable.password_off
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(painter = painterResource(id = image), "",
                    Modifier
                        .height(20.dp)
                        .width(20.dp))
            }
        })
}