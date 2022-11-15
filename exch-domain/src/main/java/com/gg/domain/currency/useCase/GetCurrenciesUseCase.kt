package com.gg.domain.currency.useCase

import com.gg.domain.core.UseCaseWithoutParam
import com.gg.domain.currency.model.ICurrency
import com.gg.domain.currency.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetCurrenciesUseCase(private val repository: ICurrencyRepository) :
    UseCaseWithoutParam<List<ICurrency>> {
    override fun execute(): Flow<List<ICurrency>> {
        return repository.getCurrencies()
    }
}
