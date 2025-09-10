package org.dosys.project.presentation.feature.base

import kotlinx.coroutines.CoroutineScope
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

abstract class BaseStore<S: BaseState, SE: BaseSideEffect>(
    scope: CoroutineScope,
    initialState: S
): ContainerHost<S, SE> {

    final override val container = scope.container<S, SE>(initialState)
}