package com.gg.domain.core

import kotlinx.coroutines.flow.Flow

interface UseCase<PARAM, MODEL> {
    fun execute(param: PARAM): Flow<MODEL>
}

interface UseCaseWithoutParam<MODEL> {
    fun execute(): Flow<MODEL>
}