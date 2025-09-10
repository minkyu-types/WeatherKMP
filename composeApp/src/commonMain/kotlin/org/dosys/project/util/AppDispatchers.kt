package org.dosys.project.util

import kotlinx.coroutines.CoroutineDispatcher

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDispatchers {
    val Main: CoroutineDispatcher
    val Io: CoroutineDispatcher
}