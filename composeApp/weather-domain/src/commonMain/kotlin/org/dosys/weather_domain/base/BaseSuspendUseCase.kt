package org.dosys.weather_domain.base

internal interface BaseSuspendUseCase<in I, out O> {
    suspend operator fun invoke(input: I): O
}