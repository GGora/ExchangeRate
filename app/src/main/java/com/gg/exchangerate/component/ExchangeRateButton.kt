package com.gg.exchangerate.component

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.gg.exchangerate.ui.theme.ExchangeRateTheme

@Composable
fun ExcRateButton1(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    width: Dp? = null,
    height: Dp? = null,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ExchangeRateTheme.colors.button,
            disabledBackgroundColor = ExchangeRateTheme.colors.button.copy(alpha = 0.3f)
        )
    ) {
        Text(text)
    }
}
