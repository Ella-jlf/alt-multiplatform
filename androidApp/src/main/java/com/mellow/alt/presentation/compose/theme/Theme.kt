package com.mellow.alt.presentation.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class AltAppColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val secondaryText: Color,
)

data class AltAppShapes(
    val primaryShape: Shape,
    val secondaryShape: Shape,
    val primarySmallShape: Shape,
    val secondarySmallShape: Shape,
)

data class AltAppTypography(
    val primaryTypography: TextStyle,
    val secondaryTypography: TextStyle
)

object AltAppTheme {
    val colors: AltAppColors
        @Composable
        get() = LocalAltAppColors.current
    val shapes: AltAppShapes
        @Composable
        get() = LocalAltAppShapes.current
    val typography: AltAppTypography
        @Composable
        get() = LocalAltAppTypography.current
}

val LocalAltAppColors = staticCompositionLocalOf<AltAppColors> {
    error("Colors not provided")
}
val LocalAltAppShapes = staticCompositionLocalOf<AltAppShapes> {
    error("Shapes not provided")
}
val LocalAltAppTypography = staticCompositionLocalOf<AltAppTypography> {
    error("Fonts not provided")
}