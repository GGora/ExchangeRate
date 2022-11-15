package com.gg.data.currencies.datasource

import android.util.Log
import com.gg.database.db.dao.CurrenciesDao
import com.gg.database.db.entity.Currency
import com.gg.database.db.entity.CurrencyValue
import com.gg.domain.currency.model.ICurrencyMeta
import com.gg.network.api.CurrenciesApi
import com.gg.network.model.CurrencyCodes
import com.gg.network.model.CurrencyValues
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class CurrenciesDataSource(
    private val currenciesDao: CurrenciesDao,
    private val currenciesApi: CurrenciesApi
) {

    init {
        Log.i(TAG, "Init")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val symbols = currenciesApi.getCodes().toCurrency()
                currenciesDao.updateSymbols(symbols)
            } catch (e: Exception) {
                Log.e(TAG, "Error while update symbols", e)
            }
        }
    }

    fun getCurrencies(): Flow<List<Currency>> {
        return currenciesDao.getAll()
    }

    suspend fun setFavourite(currency: ICurrencyMeta) {
        currenciesDao.setFavourite(currency.code)
    }

    suspend fun resetFavourite(currency: ICurrencyMeta) {
        currenciesDao.resetFavourite(currency.code)
    }

    suspend fun updateCurrenciesValues(baseCode: String) = withContext(Dispatchers.IO) {
        val currencies = currenciesApi.getLatest(baseCode)
        currenciesDao.updateValues(currencies.toCurrencyValue())
    }

    companion object {
        private val TAG = CurrenciesDataSource::class.java.simpleName
    }
}

fun CurrencyCodes.toCurrency(): List<Currency> {
    return symbols.map {
        Currency(
            code = it.key,
            description = it.value
        )
    }
}

fun CurrencyValues.toCurrencyValue(): List<CurrencyValue> {
    return rates.map {
        CurrencyValue(
            code = it.key,
            value = it.value.toFloat()
        )
    }
}