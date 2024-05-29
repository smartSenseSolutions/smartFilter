package com.ss.smartfilterdemo.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = purple200,
    secondary = teal200,
    tertiary = purple700,
    error = Color(0xFFB3261E),
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF201F24),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color(0xFFE6E1E5),
    onSurface = Color(0xFFE6E1E5),
    onError = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = purple500,
    secondary = teal200,
    tertiary = purple700,
    error = Color(0xFFF2B8B5),
    surface = Color(0xFFE6E1E5),
    background = Color(0xFFE6E1E5),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF201F24),
    onSurface = Color(0xFF201F24),
    onError = Color(0xFF601410)
)

@Composable
fun smartFilterTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}