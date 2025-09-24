package org.dosys.project.presentation.feature.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

abstract class BaseStore<S: BaseState, SE: BaseSideEffect>(
    scope: CoroutineScope,
    initialState: S
): ContainerHost<S, SE> {

    final override val container = scope.container<S, SE>(initialState)

    protected val storeScope = scope

    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    protected inline fun setState(reducer: S.() -> S) {
        _state.update { it.reducer() }
    }

    private val _effect = MutableSharedFlow<SE>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val effect: SharedFlow<SE> = _effect.asSharedFlow()

    protected fun postEffect(effect: SE) {
        // 논블로킹 전송. 버퍼가 꽉 차면 가장 오래된 것이 드롭됨.
        _effect.tryEmit(effect)
    }
}