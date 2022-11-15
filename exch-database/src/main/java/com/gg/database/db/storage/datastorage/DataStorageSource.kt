package com.gg.database.db.storage.datastorage

import android.content.Context
import android.util.Log
import com.gg.domain.enums.SortType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class DataStorageSource(context: Context) {
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    private val _baseCurrencyCode =
        MutableSharedFlow<String?>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val baseCurrencyCode = _baseCurrencyCode.asSharedFlow()

    private val _sortType =
        MutableSharedFlow<String?>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val sortType = _sortType.map { name ->
        try {
            name?.let {
                SortType.valueOf(it)
            } ?: SortType.NAME_ASC
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Error on get sort type", e)
            SortType.NAME_ASC
        }
    }.shareIn(CoroutineScope(Dispatchers.IO), SharingStarted.Lazily, 1)

    fun setBaseCurrencyCode(code: String) {
        with(preferences.edit()) {
            putString(PREFERENCE_BASE_CODE, code)
            apply()
        }
        _baseCurrencyCode.tryEmit(code)
    }

    fun getBaseCurrencyCode(): SharedFlow<String?> {
        _baseCurrencyCode.tryEmit(getBaseCurrencyCodeFromPreferences())
        return baseCurrencyCode
    }

    private fun getBaseCurrencyCodeFromPreferences(): String? {
        return preferences.getString(PREFERENCE_BASE_CODE, DEFAULT_BASE_VALUE)
    }

    fun setSortTypeCode(type: SortType) {
        with(preferences.edit()) {
            putString(PREFERENCE_SORT_TYPE, type.name)
            apply()
        }
        _sortType.tryEmit(type.name)
    }

    fun getSortType(): SharedFlow<SortType> {
        _sortType.tryEmit(getSortTypeFromPreferences())
        return sortType
    }

    private fun getSortTypeFromPreferences(): String? {
        return preferences.getString(PREFERENCE_SORT_TYPE, SortType.NAME_ASC.name)
    }

    companion object {
        private val TAG = DataStorageSource::class.simpleName
        private val PREFERENCES_NAME = "EXC_RATE_PREF"
        private val PREFERENCE_BASE_CODE = "PREF_BASE_CODE"
        private val PREFERENCE_SORT_TYPE = "PREF_SORT_TYPE"
        private val DEFAULT_BASE_VALUE = "USD"
    }
}

