package com.gg.domain.currency.useCase

import com.gg.domain.currency.repository.ICurrencyRepository

class UpdateCurrenciesListUseCase(private val repository: ICurrencyRepository) {
    suspend fun execute() {
        repository.updateCurrenciesList()
    }
}

