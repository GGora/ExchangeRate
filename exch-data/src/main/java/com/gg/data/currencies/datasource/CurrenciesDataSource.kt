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
        Log.d(TAG, "Init")
        val symbols = testSymbols.toCurrency()
        CoroutineScope(Dispatchers.IO).launch {
            try {
//                val symbols = currenciesApi.getCodes().toCurrency()
                currenciesDao.updateSymbols(symbols)
            } catch (e: Exception){
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
//        val currencies = currenciesApi.getLatest(baseCode)
        val currencies = getTestCV()
        currenciesDao.updateValues(currencies.toCurrencyValue())
    }

    companion object {
        private val TAG = "GGLOG ${CurrenciesDataSource::class.java.simpleName}"

        @JvmName("getTestCV1")
        fun getTestCV(): CurrencyValues {
            return CurrencyValues(
                base = "RUB",
                date = "2022-11-14",
                rates =
                mapOf(
                    "AED" to Random.nextDouble(),
                    "AFN" to Random.nextDouble(),
                    "ALL" to Random.nextDouble(),
                    "AMD" to Random.nextDouble(),
                    "ANG" to Random.nextDouble(),
                    "AOA" to Random.nextDouble(),
                    "ARS" to Random.nextDouble(),
                    "AUD" to Random.nextDouble()
                )
            )
        }

        val testSymbols = CurrencyCodes(
            symbols = mapOf(
                "AED" to "United Arab Emirates Dirham",
                "AFN" to "Afghan Afghani",
                "ALL" to "Albanian Lek",
                "AMD" to "Armenian Dram",
                "ANG" to "Netherlands Antillean Guilder",
                "AOA" to "Angolan Kwanza",
                "ARS" to "Argentine Peso",
                "AUD" to "Australian Dollar"
            )
        )
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