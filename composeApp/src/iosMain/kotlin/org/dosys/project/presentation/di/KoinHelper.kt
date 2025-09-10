package org.dosys.project.presentation.di

import org.koin.core.context.startKoin

object KoinHelper {

    fun doInitKoin() {
        startKoin {
            modules(appModule())
        }
    }
}