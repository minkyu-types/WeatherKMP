package org.dosys.base_domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen

abstract class BaseFlowUseCaseImpl<in I, out O>(
    private val dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<I, O> {

    override fun invoke(input: I): Flow<O> {
        return flow {
            emit(execute(input))
        }.retryWhen { e, attempt ->
            true
        }.catch { e ->
            e.printStackTrace()
            emit(onError(e))
        }.flowOn(dispatcher)
    }

    protected abstract suspend fun execute(input: I): O

    protected open fun onError(e: Throwable): O = throw e
}