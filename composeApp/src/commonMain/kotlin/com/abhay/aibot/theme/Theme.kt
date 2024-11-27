package com.abhay.aibot.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

private val lightScheme = Colors(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryVariant = surfaceVariantLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryVariant = onSecondaryLight,
    error = errorLight,
    onError = onErrorLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    isLight = true,
)

private val darkScheme = Colors(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryVariant = surfaceVariantDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryVariant = onSecondaryDark,
    error = errorDark,
    onError = onErrorDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    isLight = false
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) {
        darkScheme
    } else {
        lightScheme
    }

    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}

