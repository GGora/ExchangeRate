package com.gg.database.db.dao

import androidx.room.*
import com.gg.database.db.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDao {

    @Query("SELECT * FROM $CURRENCIES_TABLE_NAME" )
    fun getAll(): Flow<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateSymbols(currencies: List<Currency>)

    @Update(entity = Currency::class)
    suspend fun updateValues(currencyValues: List<CurrencyValue>)

    @Query("UPDATE $CURRENCIES_TABLE_NAME " +
            "SET $CURRENCY_FIELD_FAVOURITE = 1 " +
            "WHERE $CURRENCY_FIELD_CODE = :code")
    suspend fun setFavourite(code: String)

    @Query("UPDATE $CURRENCIES_TABLE_NAME " +
            "SET $CURRENCY_FIELD_FAVOURITE = 0 " +
            "WHERE $CURRENCY_FIELD_CODE = :code")
    suspend fun resetFavourite(code: String)
}