package com.gg.domain.storage.useCase

import com.gg.domain.enums.SortType
import com.gg.domain.storage.repository.IDataStorageRepository

class SetSortTypeUseCase(private val repository: IDataStorageRepository) {
    fun execute(sortType: SortType) {
        repository.setSortTypeCode(sortType)
    }
}