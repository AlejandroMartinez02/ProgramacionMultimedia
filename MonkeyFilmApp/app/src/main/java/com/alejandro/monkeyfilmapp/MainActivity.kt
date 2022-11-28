package com.alejandro.monkeyfilmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alejandro.monkeyfilmapp.ui.screens.MonkeyNavigator
import com.alejandro.monkeyfilmapp.ui.theme.MonkeyFilmAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonkeyFilmAppTheme {
                MonkeyNavigator()
            }
        }
    }
}


