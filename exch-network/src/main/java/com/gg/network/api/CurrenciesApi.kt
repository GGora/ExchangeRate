package com.gg.network.api

import com.gg.network.model.CurrencyCodes
import com.gg.network.model.CurrencyValues
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {
    @GET("/exchangerates_data/latest")
    suspend fun getLatest(@Query("base") baseCurrencyCode: String): CurrencyValues

    @GET("/exchangerates_data/symbols")
    suspend fun getCodes(): CurrencyCodes
}