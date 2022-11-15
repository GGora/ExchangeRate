package com.gg.domain.enums

enum class SortType(val sortField: SortField, val direction: SortDirection) {
    NAME_ASC(SortField.BY_NAME, SortDirection.ASCENDING),
    NAME_DES(SortField.BY_NAME, SortDirection.DESCENDING),
    VALUE_ASC(SortField.BY_VALUE, SortDirection.ASCENDING),
    VALUE_DES(SortField.BY_VALUE, SortDirection.DESCENDING),
    UNKNOWN(SortField.UNKNOWN, SortDirection.UNKNOWN)
}
