package com.samsung.weather_data.di

import com.samsung.weather_data.remote.repositoryImpl.WeatherRepositoryImpl
import org.dosys.weather_domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            getCurrentWeatherApi = get(),
            getWeatherFor5DaysApi = get(),
            currentWeatherMapper = get(),
            weatherFor5DaysMapper = get(),
        )
    }
}