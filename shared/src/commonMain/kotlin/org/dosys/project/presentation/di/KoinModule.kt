package org.dosys.project.presentation.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.dosys.weather_domain.usecase.GetCurrentWeatherUseCase
import org.dosys.weather_domain.usecase.GetWeathersFor5DaysUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.dsl.onClose

val AppScope = named("AppScope")
val IODispatcher = named("IO")
val DefaultDispatcher = named("Default")

val coroutineModule = module {
    single(IODispatcher)      { Dispatchers.IO }
    single(DefaultDispatcher) { Dispatchers.Default }
}

val scopeModule = module {
    single<CoroutineScope>(AppScope) {
        CoroutineScope(
            SupervisorJob() +
                    get<CoroutineDispatcher>(DefaultDispatcher) +
                    CoroutineName("AppScope")
        )
    }.onClose { scope -> scope?.cancel() }
}