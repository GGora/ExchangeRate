package com.gg.database.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gg.domain.currency.model.ICurrencyMeta
import com.gg.domain.currency.model.ICurrency

const val CURRENCIES_TABLE_NAME = "currencies"
const val CURRENCY_FIELD_CODE = "code"
const val CURRENCY_FIELD_DESC = "description"
const val CURRENCY_FIELD_VALUE = "value"
const val CURRENCY_FIELD_FAVOURITE = "is_favourite"

@Entity(tableName = CURRENCIES_TABLE_NAME)
data class Currency(
    @PrimaryKey
    @ColumnInfo(name = CURRENCY_FIELD_CODE)
    override val code: String,
    @ColumnInfo(name = CURRENCY_FIELD_DESC)
    override val description: String,
    @ColumnInfo(name = CURRENCY_FIELD_VALUE)
    override val value: Float = 0f,
    @ColumnInfo(name = CURRENCY_FIELD_FAVOURITE)
    override var isFavourite: Boolean = false
) : ICurrency

data class CurrencyMeta(
    override val description: String,
    override val code: String
) : ICurrencyMeta

data class CurrencyValue(
    val code: String,
    val value: Float
)