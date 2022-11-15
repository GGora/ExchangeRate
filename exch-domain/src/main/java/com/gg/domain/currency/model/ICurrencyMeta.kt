package com.gg.domain.currency.model

data class CurrencyMeta(
    override val description: String,
    override val code: String
) : ICurrencyMeta

interface ICurrencyMeta {
    val description: String
    val code: String
}
