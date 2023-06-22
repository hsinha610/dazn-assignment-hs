package com.hsinha610.daznassignmenths.ui.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    ) {
        content()
    }
}

val DarkThemeColors = darkColors(surface = Color(0xFF1F1E1E), background = Color(0xFF1F1E1E))
val LightThemeColors = lightColors()