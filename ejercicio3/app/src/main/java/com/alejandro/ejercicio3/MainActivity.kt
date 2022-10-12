package com.alejandro.ejercicio3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.alejandro.ejercicio3.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ejercicio3Theme {
                ejercicio3()
            }
        }
    }

    @Composable
    @Preview
    fun ejercicio3(){
        Column{
            Text(text = "No se crear formas",
            style = tipoAlex.body2)
            Text(text = "Tambien tengo este color",
                color = moradoOscuro,
                fontSize = 20.sp)
            Text(text = "Utilizar estos ficheros me parece mucho más " +
                    "cómodo en vez de utilizar los archivos xml",
            style = tipoAlex.body1
            )

        }
    }
}