package com.gg.exchangerate.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ExchangeRateTheme(
    colorSystem: ExchangeRateColors = ExchangeRateColorsLight,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        ExchangeRateColorSystem provides colorSystem,
    ) {
        MaterialTheme(
            content = content
        )
    }

    MaterialTheme(
        content = content
    )
}

object ExchangeRateTheme {
    val colors: ExchangeRateColors
        @Composable
        get() = ExchangeRateColorSystem.current
}