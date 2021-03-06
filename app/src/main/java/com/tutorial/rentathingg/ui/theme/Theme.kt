package com.tutorial.rentathingg.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = MainColor,
    primaryVariant = MainColor,
    secondary = MainColor
)
private val DarkColorPalette = darkColors(
    primary = MainColor,
    primaryVariant = MainColor,
    secondary = MainColor
)
@Composable
fun RentAThinggTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
