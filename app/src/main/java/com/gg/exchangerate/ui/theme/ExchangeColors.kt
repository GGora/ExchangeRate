package com.gg.exchangerate.ui.theme


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


@Immutable
data class ExchangeRateColors(
    val background1: Color,
    val background2: Color,
    val button: Color,
    val gridBackground1: Color,
    val gridBackground2: Color,
)

val ExchangeRateColorsLight = ExchangeRateColors(
    background1 = Color(0xFF969696),
    background2 = Color(0xFFD6D6D6),
    button = Color(0xFF03A9F4),
    gridBackground1 = Color(0xFF301060),
    gridBackground2 = Color(0xFF200030)
)

val ExchangeRateColorsDark = ExchangeRateColors(
    background1 = Color(0xFF252525),
    background2 = Color(0xFF6F6F6F),
    button = Color(0xFF3F51B5),
    gridBackground1 = Color(0xFF301060),
    gridBackground2 = Color(0xFF200030)
)

val ExchangeRateColorSystem: ProvidableCompositionLocal<ExchangeRateColors> =
    staticCompositionLocalOf { ExchangeRateColorsLight }
