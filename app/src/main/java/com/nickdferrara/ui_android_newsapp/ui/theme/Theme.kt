package com.nickdferrara.ui_android_newsapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    error = md_theme_light_error,
    background = md_theme_light_background,
)

val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    error = md_theme_dark_error,
    background = md_theme_dark_background,
)

@Composable
fun UiandroidnewsappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (darkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = NewsifyShapes,
        typography = NewsifyTypography,
        content = content
    )
}