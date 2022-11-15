package com.gg.domain.currency.useCase

import com.gg.domain.currency.model.ICurrency
import com.gg.domain.currency.repository.ICurrencyRepository

class SetFavouriteUseCase(private val repository: ICurrencyRepository) {
    suspend fun execute(currency: ICurrency) {
        repository.setFavourite(currency)
    }
}