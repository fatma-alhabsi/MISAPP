package com.example.misapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = MISPrimary,
    secondary = MISSecondary,
    tertiary = MISAccent,

background = MISBackground,
surface = MISSurface,

onPrimary = MISSurface,
onSecondary = MISSurface,
onTertiary = MISSurface,

onBackground = MISTextPrimary,
onSurface = MISTextPrimary


)

private val DarkColorScheme = darkColorScheme(
    primary = MISPrimary,
    secondary = MISSecondary,
    tertiary = MISAccent,


background = MISTextPrimary,
surface = ColorDarkSurface,

onPrimary = MISSurface,
onSecondary = MISSurface,
onTertiary = MISSurface,

onBackground = MISSurface,
onSurface = MISSurface

)

@Composable
fun MISAPPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )


}
