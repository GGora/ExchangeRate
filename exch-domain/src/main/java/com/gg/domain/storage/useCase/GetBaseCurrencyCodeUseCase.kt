package com.gg.domain.storage.useCase

import com.gg.domain.core.UseCaseWithoutParam
import com.gg.domain.storage.repository.IDataStorageRepository
import kotlinx.coroutines.flow.Flow

class GetBaseCurrencyCodeUseCase(private val repository: IDataStorageRepository) :
    UseCaseWithoutParam<String?> {
    override fun execute(): Flow<String?> {
        return repository.getBaseCurrencyCode()
    }
}