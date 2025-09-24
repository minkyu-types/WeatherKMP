package org.dosys.base_domain

internal interface BaseSuspendUseCase<in I, out O> {
    suspend operator fun invoke(input: I): O
}