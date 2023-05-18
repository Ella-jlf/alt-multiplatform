package com.mellow.alt.presentation.compose.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val baseLightPalette = AltAppColors(
    primaryText = Color.Black,
    primaryBackground = Color.White,
    secondaryBackground = Color.Gray,
    secondaryText = Color.Blue
)

val baseDarkPalette = AltAppColors(
    primaryText = Color.White,
    primaryBackground = Color.Black,
    secondaryBackground = Color.Gray,
    secondaryText = Color.LightGray
)

val baseShapes = AltAppShapes(
    primaryShape = RoundedCornerShape(16.dp),
    secondaryShape = RoundedCornerShape(8.dp),
    primarySmallShape = RoundedCornerShape(48.dp),
    secondarySmallShape = RoundedCornerShape(32.dp),
)

val baseTypography = AltAppTypography(
    primaryTypography = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
    secondaryTypography = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
)