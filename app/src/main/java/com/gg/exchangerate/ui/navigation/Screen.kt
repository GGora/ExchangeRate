package com.gg.exchangerate.ui.navigation

const val ROOT_ROUTE = "root"
const val EXCHANGE_ROUTE = "exchange"

sealed class Screen(val route: String) {
    object MainExchange: Screen("main_exchange_screen")
    object About: Screen ("about_screen")
}
