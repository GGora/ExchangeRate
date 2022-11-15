package com.gg.domain.currency.repository

import com.gg.domain.currency.model.ICurrency
import com.gg.domain.currency.model.ICurrencyMeta
import kotlinx.coroutines.flow.Flow

interface ICurrencyRepository {
    fun getCurrencies(): Flow<List<ICurrency>>
    suspend fun setFavourite(currency: ICurrencyMeta)
    suspend fun removeFavourite(currency: ICurrencyMeta)
    suspend fun updateCurrenciesValues(baseCode: String)
}