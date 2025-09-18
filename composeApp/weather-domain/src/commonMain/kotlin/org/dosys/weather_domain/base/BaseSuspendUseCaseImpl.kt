package org.dosys.weather_domain.base

import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseSuspendUseCaseImpl<in I, out O>(
    private val dispatcher: CoroutineDispatcher
): BaseSuspendUseCase<I, O> {

    override suspend fun invoke(input: I): O {
        return kotlin.runCatching {
            execute(input)
        }.onFailure { e ->
            e.printStackTrace()
            onError(e)
        }.getOrThrow()
    }

    protected abstract suspend fun execute(input: I): O

    protected open fun onError(e: Throwable): O = throw e
}