package com.gg.domain.storage.useCase

import com.gg.domain.core.UseCaseWithoutParam
import com.gg.domain.enums.SortType
import com.gg.domain.storage.repository.IDataStorageRepository
import kotlinx.coroutines.flow.Flow

class GetSortTypeUseCase(private val repository: IDataStorageRepository) :
    UseCaseWithoutParam<SortType> {
    override fun execute(): Flow<SortType> {
        return repository.getSortType()
    }
}