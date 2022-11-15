package com.gg.domain.storage.useCase

import com.gg.domain.currency.repository.ICurrencyRepository
import com.gg.domain.storage.repository.IDataStorageRepository

class SetBaseCurrencyCodeUseCase(
    private val dataRepository: IDataStorageRepository,
    private val currRepository: ICurrencyRepository
) {
    suspend fun execute(code: String) {
        dataRepository.setBaseCurrencyCode(code)
        currRepository.updateCurrenciesValues(code)
    }
}