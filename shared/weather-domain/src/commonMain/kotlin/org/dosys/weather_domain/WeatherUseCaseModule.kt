package org.dosys.weather_domain

import org.dosys.weather_domain.usecase.GetCurrentWeatherUseCase
import org.dosys.weather_domain.usecase.GetWeathersFor5DaysUseCase
import org.koin.dsl.module

val weatherUsecaseModule = module {
    single { GetCurrentWeatherUseCase(repository = get()) }
    single { GetWeathersFor5DaysUseCase(repository = get()) }
}