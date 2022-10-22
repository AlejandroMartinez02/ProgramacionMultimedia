package com.alejandro.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.alejandro.layouts.ui.theme.LayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsTheme {
                Layout()
            }
        }
    }
}

@Composable
@Preview
fun Layout(){
    Column(Modifier.fillMaxSize()){

        Box(Modifier.background(Color.Cyan)
            .weight(1F)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center){
            Text("Ejemplo 1")
        }

        Row(Modifier.weight(1F)){
            Box(Modifier.background(Color.Green)
                .weight(1F)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center){
                Text("Ejemplo 2")
            }
            Box(Modifier.background(Color.Red)
                .weight(1F)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center){
                Text("Ejemplo 3")
            }
        }

        Box(Modifier.background(Color.Magenta)
            .weight(1F)
            .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter){
            Text("Ejemplo 4")
        }

    }
}