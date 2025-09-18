package org.dosys.project.presentation.di

import org.dosys.project.presentation.feature.weather.main.WeatherMainStoreFactory
import org.koin.dsl.module

val storeModule = module {
    single {
        WeatherMainStoreFactory(
            getCurrentWeatherUseCase = get(),
            getWeathersFor5DaysUseCase = get(),
            currentWeatherMapper = get(),
            weatherFor5DaysMapper = get()
        )
    }
}