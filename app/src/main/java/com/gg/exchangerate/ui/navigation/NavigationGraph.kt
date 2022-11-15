package com.gg.exchangerate.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

@Composable
fun SetupNavigationGraph(
    navController: NavHostController
) {
    val startRoute by remember { mutableStateOf(EXCHANGE_ROUTE) }

    NavHost(
        navController = navController,
        startDestination = startRoute,
        route = ROOT_ROUTE
    ){
        exchangeNavGraph(navController = navController)
    }

}