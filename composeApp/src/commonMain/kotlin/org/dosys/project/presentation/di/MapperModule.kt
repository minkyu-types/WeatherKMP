package org.dosys.project.presentation.di

import org.dosys.project.presentation.mapper.CurrentWeatherMapper
import org.dosys.project.presentation.mapper.WeatherFor5DaysMapper
import org.koin.dsl.module

val composeAppMapperModule = module {
    single { CurrentWeatherMapper() }
    single { WeatherFor5DaysMapper() }
}