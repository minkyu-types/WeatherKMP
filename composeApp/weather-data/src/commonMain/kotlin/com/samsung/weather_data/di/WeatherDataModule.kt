package com.samsung.weather_data.di

import com.samsung.weather_data.remote.mapper.CurrentAndForecastsWeatherMapper
import com.samsung.weather_data.remote.mapper.DailyAggregationMapper
import com.samsung.weather_data.remote.mapper.WeatherByTimestampMapper
import com.samsung.weather_data.remote.mapper.WeatherOverviewMapper
import com.samsung.weather_data.repositoryImpl.WeatherRepositoryImpl
import org.dosys.weather_domain.repository.WeatherRepository
import org.koin.dsl.module

val weatherDataModule = module {
    single { CurrentAndForecastsWeatherMapper() }
    single { DailyAggregationMapper() }
    single { WeatherByTimestampMapper() }
    single { WeatherOverviewMapper() }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            getCurrentAndForecastsWeatherApi = get(),
            getDailyAggregationApi = get(),
            getWeatherByTimestampApi = get(),
            getWeatherOverviewApi = get(),
            currentAndForecastsWeatherMapper = get(),
            dailyAggregationMapper = get(),
            weatherByTimestampMapper = get(),
            weatherOverviewMapper = get()
        )
    }
}