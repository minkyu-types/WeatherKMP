@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.dosys.project.presentation.core.util

import kotlinx.coroutines.CoroutineDispatcher

expect object AppDispatchers {
    val Main: CoroutineDispatcher
    val Io: CoroutineDispatcher
}