package es.amm.monkeyfilmlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.amm.monkeyfilmlab.ui.composable.MediaGridView
import es.amm.monkeyfilmlab.ui.composable.MediaListView
import es.amm.monkeyfilmlab.ui.composable.MediaListViewWidthCustomControl
import es.amm.monkeyfilmlab.ui.theme.MonkeyFilmLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonkeyFilmLabTheme {
                MediaListViewWidthCustomControl()
            }
        }
    }
}

