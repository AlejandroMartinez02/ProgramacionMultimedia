package com.dam2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.dam2.ui.theme.TextTextFieldTheme
import com.dam2.ui.theme.anton
import com.dam2.ui.theme.silk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextTextFieldTheme {
               Column {
                   Text1()
                   Text2()
                   Text3()
                   Text4()
                   Text5()
                   TextField1()
                   TextField2()
                   TextField3()
                   TextField4()
                   TextField5()
               }
            }
        }
    }


    @Composable
    @Preview
    fun Text1(){
            Text(text = "Este es el texto 1",
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 4.sp
        )
    }
    @Composable
    @Preview
    fun Text2(){
        Text(text = "Este texto está tachado y rosa",
            textDecoration = TextDecoration.LineThrough,
            color = Color.Magenta,
            fontSize = 20.sp,
            lineHeight = 10.sp,
            fontFamily = anton
        )
    }

    @Composable
    @Preview
    fun Text3(){
        val offset = Offset(10.0f, 4.0f)
        Text(text = "Hola! Tengo sombra :0",
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = offset,
                    blurRadius = 3f
                ),
                color = Color.White,
            )
        )
    }

    @Composable
    @Preview
    fun Text4(){
        val offset = Offset(5.0f, 4.0f)
        Text(text = "¡Ahora parezco un cartel luminoso!",
        style = TextStyle(
            shadow = Shadow(
                color = Color.Magenta,
                offset = offset,
                blurRadius = 20F
            ),
            fontFamily = silk,
            color = Color.Yellow,
            fontSize = 25.sp
        ))
    }

    @Composable
    @Preview
    fun Text5(){
        val offset = Offset(5.0f, 4.0f)
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue, fontSize = 10.sp)){
                append("es")
            }
            append("te texto es")
            withStyle(style = SpanStyle(shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 20F
            ), color = Color.Red)
            ){
                append(" algo ra")
            }
            append("ro")
        })

    }

    @Composable
    @Preview
    fun TextField1(){
        var text by remember{ mutableStateOf("") }
            TextField(value = text,
            onValueChange = { text = it },
            label = {Text("Primer TextField")},
            textStyle = TextStyle(fontFamily = anton),
            maxLines = 1,
            modifier = Modifier.border(BorderStroke(5.dp, Color.Blue))

        )
    }

    @Composable
    @Preview
    fun TextField2(){

        TextField(value = "¡En este no puedes escribir!",
            onValueChange = { it },
            label = {Text("Deshabilitado", fontFamily = silk)},
            enabled = false,
            textStyle = TextStyle(color = Color.Magenta, fontFamily = silk),
            modifier = Modifier.border(BorderStroke(5.dp, Color.Red))
        )
    }

    @Composable
    @Preview
    fun TextField3(){

        OutlinedTextField(value = "Es igual al anterior pero Outlined",
            onValueChange = { it },
            label = {Text("Deshabilitado", fontFamily = silk)},
            enabled = false,
            textStyle = TextStyle(color = Color.Blue, fontFamily = silk)
        )
    }

    @Composable
    @Preview
    fun TextField4(){
        var text by remember{ mutableStateOf("") }
        TextField(value = text,
            onValueChange = { text = it },
            label = {Text("Contraseñaaaaaaaa", fontFamily = silk)},
            textStyle = TextStyle(color = Color.Blue, fontFamily = silk) ,
            visualTransformation = PasswordVisualTransformation()
        )
    }



    @Composable
    @Preview
    fun TextField5(){
        var text by remember{ mutableStateOf("") }
        TextField(value = text,
            onValueChange = { text = it },
            label = {Text("Ultimo TextField")},
            textStyle = TextStyle(fontFamily = FontFamily.Cursive, color = Color.Yellow),
            maxLines = 1
        )
    }
}


