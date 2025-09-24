package org.dosys.project

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlatformModuleHelper: KoinComponent {
    private val platform: Platform by inject()
    fun name(): String = platform.name
}