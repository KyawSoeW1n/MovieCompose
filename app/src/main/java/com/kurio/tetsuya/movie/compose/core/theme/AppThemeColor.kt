package com.kurio.tetsuya.movie.compose.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.kurio.tetsuya.movie.compose.core.color.GreyLight
import com.kurio.tetsuya.movie.compose.core.color.Pink40
import com.kurio.tetsuya.movie.compose.core.color.Pink80
import com.kurio.tetsuya.movie.compose.core.color.Purple40
import com.kurio.tetsuya.movie.compose.core.color.Purple80
import com.kurio.tetsuya.movie.compose.core.color.PurpleGrey40
import com.kurio.tetsuya.movie.compose.core.color.PurpleGrey80


val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onBackground = GreyLight,
    surfaceTint = Color.White,
)

val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    onBackground = Color.White,
    surfaceTint = Color.Black
)
