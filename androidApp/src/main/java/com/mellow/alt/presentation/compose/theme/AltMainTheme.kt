package com.mellow.alt.presentation.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AltMainTheme(
    content: @Composable () -> Unit
) {
    val colors = when (isSystemInDarkTheme()) {
        true -> {
            baseDarkPalette
        }
        false -> {
            baseLightPalette
        }
    }

    val shapes = baseShapes
    val typography = baseTypography

    CompositionLocalProvider(
        LocalAltAppColors provides colors,
        LocalAltAppShapes provides shapes,
        LocalAltAppTypography provides typography,
        content = content,
    )
}