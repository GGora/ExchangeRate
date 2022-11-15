package com.gg.exchangerate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gg.exchangerate.ui.navigation.SetupNavigationGraph
import com.gg.exchangerate.ui.theme.ExchangeRateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .clickable(false) {}
                .background(color = ExchangeRateTheme.colors.background1)
            ) {
                SetupNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
