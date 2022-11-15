package com.gg.domain.currency.model

data class Currency(
    override val code: String,
    override val description: String,
    override val value: Float,
    override var isFavourite: Boolean
) : ICurrency


interface ICurrency : ICurrencyMeta {
    val value: Float
    var isFavourite: Boolean
}

