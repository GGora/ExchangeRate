package com.gg.domain.currency.useCase

import com.gg.domain.currency.repository.ICurrencyRepository

class UpdateCurrencyValuesUseCase(private val repository: ICurrencyRepository) {
    suspend fun execute(baseCode: String) {
        repository.updateCurrenciesValues(baseCode)
    }
}