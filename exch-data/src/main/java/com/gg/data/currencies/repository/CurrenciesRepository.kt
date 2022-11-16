package com.gg.data.currencies.repository

import com.gg.data.currencies.datasource.CurrenciesDataSource
import com.gg.domain.currency.model.ICurrency
import com.gg.domain.currency.model.ICurrencyMeta
import com.gg.domain.currency.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrenciesRepository(private val dataSource: CurrenciesDataSource) : ICurrencyRepository {

    override fun getCurrencies(): Flow<List<ICurrency>> {
        return dataSource.getCurrencies()
    }

    override suspend fun setFavourite(currency: ICurrencyMeta) {
        dataSource.setFavourite(currency)
    }

    override suspend fun removeFavourite(currency: ICurrencyMeta) {
        dataSource.resetFavourite(currency)
    }

    override suspend fun updateCurrenciesValues(baseCode: String) {
        dataSource.updateCurrenciesValues(baseCode)
    }

    override suspend fun updateCurrenciesList(){
        dataSource.updateCurrenciesList()
    }
}