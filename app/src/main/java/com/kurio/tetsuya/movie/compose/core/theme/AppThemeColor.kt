package com.kurio.tetsuya.movie.compose.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.kurio.tetsuya.movie.compose.ui.theme.Pink40
import com.kurio.tetsuya.movie.compose.ui.theme.Pink80
import com.kurio.tetsuya.movie.compose.ui.theme.Purple40
import com.kurio.tetsuya.movie.compose.ui.theme.Purple80
import com.kurio.tetsuya.movie.compose.ui.theme.PurpleGrey40
import com.kurio.tetsuya.movie.compose.ui.theme.PurpleGrey80


val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
)

val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)
