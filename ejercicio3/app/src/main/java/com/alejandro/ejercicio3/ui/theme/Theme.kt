package com.alejandro.ejercicio3.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val Claros = lightColors(
    primary = verdecito,
    primaryVariant = verdeAgrisado,
    secondary = verdeClaro
)

private val Oscuros = darkColors(
    primary = verdeOscuro,
    primaryVariant = verdeOscuro2,
    secondary = moradoOscuro
)

@Composable
fun Ejercicio3Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        Oscuros
    } else {
        Claros
    }

    MaterialTheme(
        colors = colors,
        typography = tipoAlex,
        shapes = Shapes,
        content = content
    )
}