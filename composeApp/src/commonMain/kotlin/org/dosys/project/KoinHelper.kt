package org.dosys.project

import org.koin.core.context.startKoin

fun doInitKoin() {
    startKoin {
        modules(appModule())
    }
}