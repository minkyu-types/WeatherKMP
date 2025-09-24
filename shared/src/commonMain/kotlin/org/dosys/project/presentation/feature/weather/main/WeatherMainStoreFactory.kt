package org.dosys.project.presentation.feature.weather.main

import kotlinx.coroutines.CoroutineScope
import org.dosys.project.presentation.mapper.CurrentWeatherMapper
import org.dosys.project.presentation.mapper.WeatherFor5DaysMapper
import org.dosys.weather_domain.usecase.GetCurrentWeatherUseCase
import org.dosys.weather_domain.usecase.GetWeathersFor5DaysUseCase

class WeatherMainStoreFactory(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeathersFor5DaysUseCase: GetWeathersFor5DaysUseCase,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val weatherFor5DaysMapper: WeatherFor5DaysMapper
) {
    fun create(scope: CoroutineScope): WeatherMainStore =
        WeatherMainStore(
            getCurrentWeatherUseCase = getCurrentWeatherUseCase,
            getWeathersFor5DaysUseCase = getWeathersFor5DaysUseCase,
            currentWeatherMapper = currentWeatherMapper,
            weatherFor5DaysMapper = weatherFor5DaysMapper,
            scope = scope
        )
}
