package com.alejandro.progressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.progressbar.ui.theme.ProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgressBarTheme {
                MyProgressBar()
            }
        }
    }
}



@Preview
@Composable
fun MyProgressBar(){
    var showloading by rememberSaveable{
        mutableStateOf(false)
    }
    var processStatus by rememberSaveable {
        mutableStateOf(0F)
    }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        if(showloading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 3.dp,
                progress = processStatus
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Green,
                backgroundColor = Color.LightGray,
                progress = processStatus
            )
            Row(
                Modifier.padding(top = 8.dp),
                Arrangement.SpaceAround,
                Alignment.CenterVertically
            ){
                Button(
                    onClick ={
                        if(processStatus > 0f && (processStatus-0.1f) > 0f)
                            processStatus -= 0.1f
                    }) {
                    Text(text = "Reducir")
                }
                Button(
                    onClick = {
                        if(processStatus < 1f)
                            processStatus += 0.1f
                    }
                ) {
                    Text(text = "Incrementar")
                }
            }
        }


        Button(
            modifier = Modifier.padding(top = 100.dp),
            onClick = { showloading = !showloading }
        ) {
            Text(text = "Activar / Desactivar")
        }

    }
}