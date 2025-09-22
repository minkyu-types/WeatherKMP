package com.samsung.weather_data.di

import com.samsung.weather_data.remote.api.GetCurrentWeatherApi
import com.samsung.weather_data.remote.api.GetWeatherFor5DaysApi
import com.samsung.weather_data.remote.api.createGetCurrentWeatherApi
import com.samsung.weather_data.remote.api.createGetWeatherFor5DaysApi
import com.samsung.weather_data.remote.mapper.CurrentWeatherMapper
import com.samsung.weather_data.remote.mapper.WeatherFor5DaysMapper
import com.samsung.weather_data.repositoryImpl.WeatherRepositoryImpl
import de.jensklingenberg.ktorfit.Ktorfit
import org.dosys.weather_domain.repository.WeatherRepository
import org.koin.dsl.module

val weatherDataModule = module {
    single { CurrentWeatherMapper() }
    single { WeatherFor5DaysMapper() }

    single<GetCurrentWeatherApi> { get<Ktorfit>().createGetCurrentWeatherApi() }
    single<GetWeatherFor5DaysApi> { get<Ktorfit>().createGetWeatherFor5DaysApi() }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            getCurrentWeatherApi = get(),
            getWeatherFor5DaysApi = get(),
            currentWeatherMapper = get(),
            weatherFor5DaysMapper = get()
        )
    }
}