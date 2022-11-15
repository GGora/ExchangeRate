package com.gg.network.model

data class CurrencyValues(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)