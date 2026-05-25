package com.proto.mvi.ui.theme

import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val WHITE = Color(0xFFFFFFFF)
val BLACK = Color(0xFF000000)

    val Background2 = Color(0xFFF1EEEE)


data class ExtendedColors(
    val cardBackgroundColor: Color,
    val transparent: Color,
    val text: Color,
    val avatarBorderColor: Color,
)

val LightExtendedColors = ExtendedColors(
    transparent = Color(0x00000000),
    cardBackgroundColor = Color(0xFFFFFFFF),
    text = Color(0xFF000000),
    avatarBorderColor = Color(0x50000000),

    )
val DarkExtendedColors = ExtendedColors(
    transparent = Color(0x00000000),
    cardBackgroundColor = Color(0xFFFFFFFF),
    text = Color(0xFF000000),
    avatarBorderColor = Color(0x50FFFFFF),
)


