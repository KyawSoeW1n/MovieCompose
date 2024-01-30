package com.kurio.tetsuya.movie.compose.core.color

import androidx.compose.ui.graphics.Color
import com.kuriotetsuya.data.model.ColorItem

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Green = Color(0xFF54ab93)
val GreenGrey = Color(0xFF5497ab)
val LightGreen = Color(0xFF54ab68)

val Blue = Color(0xFF3512ED)
val BlueGrey = Color(0xFFA212ED)
val LightBlue = Color(0xFF125DED)

val Pink = Color(0xFFEA1598)
val PinkGrey = Color(0xFFEA152D)
val LightPink = Color(0xFFD215EA)

val Yellow = Color(0xFFE29C1D)
val YellowGrey = Color(0xFFC5E21D)
val LightYellow = Color(0xFFE23A1D)


val GreyLight = Color(0xFF7A7A7A)

val colors = listOf(
    ColorItem(
        name = "Green",
        primary = Green,
        secondary = GreenGrey,
        tertiary = LightGreen,
        onBackground = Color.White,
        surfaceTint = Color.Black
    ), ColorItem(
        name = "Blue",
        primary = Blue,
        secondary = BlueGrey,
        tertiary = LightBlue,
        onBackground = Color.White,
        surfaceTint = Color.Black
    ), ColorItem(
        name = "Pink",
        primary = Pink,
        secondary = PinkGrey,
        tertiary = LightPink,
        onBackground = Color.White,
        surfaceTint = Color.Black
    ), ColorItem(
        name = "Yellow",
        primary = Yellow,
        secondary = YellowGrey,
        tertiary = LightYellow,
        onBackground = Color.White,
        surfaceTint = Color.Black
    )
)