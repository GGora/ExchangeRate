package com.gg.exchangerate.ui.exchange

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.domain.currency.model.ICurrency
import com.gg.domain.currency.useCase.GetCurrenciesUseCase
import com.gg.domain.currency.useCase.RemoveFavouriteUseCase
import com.gg.domain.currency.useCase.SetFavouriteUseCase
import com.gg.domain.currency.useCase.UpdateCurrencyValuesUseCase
import com.gg.domain.enums.SortType
import com.gg.domain.storage.useCase.GetBaseCurrencyCodeUseCase
import com.gg.domain.storage.useCase.GetSortTypeUseCase
import com.gg.domain.storage.useCase.SetBaseCurrencyCodeUseCase
import com.gg.domain.storage.useCase.SetSortTypeUseCase
import com.gg.exchangerate.MainApplication
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ExchangeRateViewModel : ViewModel() {

    @Inject
    lateinit var getBaseCurrencyCodeUseCase: GetBaseCurrencyCodeUseCase

    @Inject
    lateinit var getSortTypeUseCase: GetSortTypeUseCase

    @Inject
    lateinit var setBaseCurrencyCodeUseCase: SetBaseCurrencyCodeUseCase

    @Inject
    lateinit var setSortTypeUseCase: SetSortTypeUseCase

    @Inject
    lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @Inject
    lateinit var removeFavouriteUseCase: RemoveFavouriteUseCase

    @Inject
    lateinit var setFavouriteUseCase: SetFavouriteUseCase

    @Inject
    lateinit var updateCurrencyValuesUseCase: UpdateCurrencyValuesUseCase

    init {
        MainApplication.appComponent.injectExchangeRateViewModel(this)
    }

    val baseCurrency = getBaseCurrencyCodeUseCase
        .execute()
        .filterNotNull()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DEFAULT_BASE_VALUE)

    private val sortType = getSortTypeUseCase
        .execute()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SortType.UNKNOWN)


    var followBaseCurrency: Job? = null


    fun setBaseCurrency(code: String) {
        viewModelScope.launch(handler) {
            setBaseCurrencyCodeUseCase.execute(code)
        }
    }

    fun setSortType(type: SortType) {
        setSortTypeUseCase.execute(type)
    }

    private val _currencies = getCurrenciesUseCase.execute()
    val codes = _currencies.map { currencies -> currencies.map { it.code }.sorted() }


    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught $exception", exception)
    }

    init {
        followBaseCurrency = viewModelScope.launch(handler) {
            baseCurrency
                .filter { it != DEFAULT_BASE_VALUE }
                .first()
                .also {
                    withContext(Dispatchers.IO) { updateCurrencyValuesUseCase.execute(it) }
                    followBaseCurrency?.cancelAndJoin()
                }
        }
    }

    val currencies = _currencies.combine(sortType) { currencies, sort ->
        when (sort) {
            SortType.NAME_ASC -> currencies.sortedBy { it.code }
            SortType.NAME_DES -> currencies.sortedByDescending { it.code }
            SortType.VALUE_ASC -> currencies.sortedBy { it.value }
            SortType.VALUE_DES -> currencies.sortedByDescending { it.value }
            else -> currencies
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val currenciesFavourite = currencies.map { it.filter { currency -> currency.isFavourite } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    suspend fun switchFavourite(currency: ICurrency) = withContext(Dispatchers.IO) {
        if (currency.isFavourite) removeFavouriteUseCase.execute(currency)
        else setFavouriteUseCase.execute(currency)
    }


    suspend fun updateCurrencyValues() = withContext(Dispatchers.IO) {
        try {
            updateCurrencyValuesUseCase.execute(baseCurrency.value)
        } catch (e: Exception) {
            Log.e(TAG, "Error while update currencies' values", e)
        }
    }

    companion object {
        private val TAG = ExchangeRateViewModel::class.java.simpleName
        private val DEFAULT_BASE_VALUE = "UNKN"
    }
}