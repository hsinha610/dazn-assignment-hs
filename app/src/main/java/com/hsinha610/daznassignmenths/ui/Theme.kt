package com.hsinha610.daznassignmenths.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun CustomAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    ) {
        content()
    }
}

val DarkThemeColors = darkColors()
val LightThemeColors = lightColors()