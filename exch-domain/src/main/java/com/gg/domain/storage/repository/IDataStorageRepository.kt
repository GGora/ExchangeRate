package com.gg.domain.storage.repository

import com.gg.domain.enums.SortType
import kotlinx.coroutines.flow.Flow

interface IDataStorageRepository {
    fun getBaseCurrencyCode(): Flow<String?>
    fun setBaseCurrencyCode(code: String)
    fun getSortType(): Flow<SortType>
    fun setSortTypeCode(type: SortType)
}