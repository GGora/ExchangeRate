package com.gg.exchangerate.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gg.exchangerate.ui.exchange.ExchangeRateViewModel
import com.gg.exchangerate.ui.exchange.screens.AboutScreen
import com.gg.exchangerate.ui.exchange.screens.MainExchangeScreen

fun NavGraphBuilder.exchangeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.MainExchange.route,
        route = EXCHANGE_ROUTE
    ) {
        composable(
            route = Screen.MainExchange.route
        ) {
            MainExchangeScreen(
                navController = navController,
                viewModel = ExchangeRateViewModel()
            )
        }

        composable(
            route = Screen.About.route
        ) {
            AboutScreen(
                navController = navController,
            )
        }
    }
}