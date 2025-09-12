package org.dosys.project.presentation.core

import kotlinx.coroutines.CoroutineDispatcher

expect object AppDispatchers {
    val Main: CoroutineDispatcher
    val Io: CoroutineDispatcher
}