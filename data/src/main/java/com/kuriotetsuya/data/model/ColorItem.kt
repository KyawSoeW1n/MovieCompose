package com.kuriotetsuya.data.model

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

data class ColorItem(
    val name: String,
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val onBackground: Color,
    val surfaceTint: Color
)

fun ColorItem.toLightColor() = lightColorScheme(
    primary = this.primary,
    secondary = this.secondary,
    onSurface = this.surfaceTint,
    onBackground = this.onBackground,
    surfaceTint = this.surfaceTint,
)

fun ColorItem.toDarkColor() = lightColorScheme(
    primary = this.primary,
    secondary = this.secondary,
    onSurface = this.surfaceTint,
    onBackground = this.onBackground,
    surfaceTint = this.surfaceTint,
)