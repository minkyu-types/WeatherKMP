package org.dosys.base_domain

import kotlinx.coroutines.flow.Flow

internal interface BaseFlowUseCase<in I, out O> {
    operator fun invoke(input: I): Flow<O>
}