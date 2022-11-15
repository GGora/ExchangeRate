package com.gg.exchangerate.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun ExcRateTextHeader(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        modifier = modifier,
        text = text,

        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ExcRateText1(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ExcRateText2(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun ExcRateText3(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    )
}