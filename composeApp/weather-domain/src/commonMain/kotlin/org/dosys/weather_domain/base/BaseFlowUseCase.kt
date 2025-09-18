package org.dosys.weather_domain.base

import kotlinx.coroutines.flow.Flow

internal interface BaseFlowUseCase<in I, out O> {
    operator fun invoke(input: I): Flow<O>
}