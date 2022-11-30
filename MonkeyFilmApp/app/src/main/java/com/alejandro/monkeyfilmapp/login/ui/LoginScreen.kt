package com.alejandro.monkeyfilmapp.login.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.R
import com.alejandro.monkeyfilmapp.ui.screens.Routes
import com.alejandro.monkeyfilmapp.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController : NavHostController) {

    val email : String by loginViewModel.email.observeAsState(initial = "")
    val password : String by loginViewModel.password.observeAsState(initial = "")
    val isButtonEnable : Boolean by loginViewModel.isButtonLoginEnable.observeAsState(initial = false)
    val isLoading : Boolean by loginViewModel.isLoading.observeAsState(initial = false)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(azulFondo)){
        if(isLoading){
            Box(modifier = Modifier
                .fillMaxSize()){
                CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color.White)
            }
        }else{
            Column(modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)) {
                HeaderImage(
                    Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.padding(16.dp))
                EmailField(email) { loginViewModel.onLoginChanged(it, password) }
                Spacer(modifier = Modifier.padding(4.dp))
                PasswordField(password){loginViewModel.onLoginChanged(email, it)}
                Spacer(modifier = Modifier.padding(10.dp))
                LoginButton(isButtonEnable){loginViewModel.onButtonLoginPress(navController)}
                Spacer(modifier = Modifier.padding(16.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                    CreateAccount(navController)
                    RememberPassword(loginViewModel)
                }

            }
        }

    }

}

@Composable
fun CreateAccount(navController: NavHostController) {
    Text(
        text = "¿No tienes cuenta aún?",
        modifier = Modifier
            .clickable { navController.navigate(Routes.Register.route) }
            .padding(start = 10.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = Color.White
    )
}

@Composable
fun LoginButton(buttonEnabled : Boolean, onLoginSelected : () -> Unit) {
    Button(onClick = { onLoginSelected() },  modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .border(2.dp, Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF202B5D),
            disabledBackgroundColor = Color(0xFF5F5F9C),
            contentColor = Color.White
        ),
        enabled = buttonEnabled) {
        Text(text = "Iniciar sesión", color = Color.White)
    }
}

@Composable
fun RememberPassword(loginViewModel: LoginViewModel) {
    val showDialog by loginViewModel.showDialog.observeAsState(false)
    Text(
        text = "¿Olvidate la contraseña?",
        modifier = Modifier
            .clickable { loginViewModel.showDialog.value = true }
            .padding(end = 10.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = Color.White
    )
    if(showDialog){
        DialogRememberPassword(loginViewModel = loginViewModel)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DialogRememberPassword(loginViewModel: LoginViewModel) {
    val forgottenEmail = loginViewModel.forgottenEmail.observeAsState("")
    var showResponse by rememberSaveable{mutableStateOf(false)}

    Dialog(onDismissRequest = {loginViewModel.hideDialog()},
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)) {
        if(showResponse){
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)){
                Text(
                    "¡Correo enviado, comprueba tu correo!",
                    fontFamily = Calibri,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            loginViewModel.viewModelScope.launch {
                delay(2000)
                loginViewModel.forgottenEmail.value = ""
                loginViewModel.hideDialog()
            }

        }else{
            Column(modifier = Modifier
                .background(Color.White)
                .padding(24.dp)){
                Text(
                    "Introduce tu correo y recibirás un correo para cambiar la contraseña",
                    fontFamily = Calibri,
                    fontSize = 18.sp
                )
            Spacer(Modifier.padding(10.dp))
                TextField(
                    value = forgottenEmail.value,
                    onValueChange = { loginViewModel.passwordForgotten(it) },
                label = {Text("Correo", fontFamily = Calibri)})
            Spacer(Modifier.padding(10.dp))
            Button(onClick = {
                if (loginViewModel.validEmail(forgottenEmail.value)) {
                    showResponse = true
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Enviar correo", fontFamily = Calibri, fontSize = 14.sp)
            }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun PasswordField(password : String, onPassChange : (String) -> Unit) {
    var passwordVisible by rememberSaveable{mutableStateOf(false)}
    TextField(
        value = password,
        onValueChange = {onPassChange(it)},
        placeholder = { Text(text = "Contraseña", color = Color.Black) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        trailingIcon = {
            val image = if (passwordVisible)
                R.drawable.password_visible
            else R.drawable.password_off
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(painter = painterResource(id =image), "",
                    Modifier
                        .height(20.dp)
                        .width(20.dp))
            }
        },
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        )
    )
}

@Composable
fun EmailField(email : String, onEmailChange : (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {onEmailChange(it)},
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = "Email", color = Color.Black) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black,
        unfocusedIndicatorColor = Color.Black,
            backgroundColor = Color.White
        )
    )
}

@Composable
fun HeaderImage(modifier : Modifier){
    Image(painter = painterResource(id = R.drawable.monkeyfilms_logo), contentDescription = "HeaderLogo", modifier = modifier)
}