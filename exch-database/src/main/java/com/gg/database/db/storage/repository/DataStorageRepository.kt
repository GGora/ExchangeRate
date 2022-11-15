package com.gg.database.db.storage.repository

import com.gg.database.db.storage.datastorage.DataStorageSource
import com.gg.domain.enums.SortType
import com.gg.domain.storage.repository.IDataStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

class DataStorageRepository(private val dataSource: DataStorageSource) : IDataStorageRepository {
    override fun getBaseCurrencyCode(): Flow<String?> = dataSource.getBaseCurrencyCode()
    override fun setBaseCurrencyCode(code: String) = dataSource.setBaseCurrencyCode(code)
    override fun getSortType(): SharedFlow<SortType> = dataSource.getSortType()
    override fun setSortTypeCode(type: SortType) = dataSource.setSortTypeCode(type)
}