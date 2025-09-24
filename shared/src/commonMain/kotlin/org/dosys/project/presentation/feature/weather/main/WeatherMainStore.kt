package org.dosys.project.presentation.feature.weather.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.dosys.project.presentation.feature.base.BaseStore
import org.dosys.project.presentation.feature.base.LoadState
import org.dosys.project.presentation.mapper.CurrentWeatherMapper
import org.dosys.project.presentation.mapper.WeatherFor5DaysMapper
import org.dosys.weather_domain.model.Language
import org.dosys.weather_domain.model.WeatherUnit
import org.dosys.weather_domain.usecase.GetCurrentWeatherUseCase
import org.dosys.weather_domain.usecase.GetWeathersFor5DaysUseCase

class WeatherMainStore(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeathersFor5DaysUseCase: GetWeathersFor5DaysUseCase,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val weatherFor5DaysMapper: WeatherFor5DaysMapper,
    scope: CoroutineScope,
) : BaseStore<WeatherMainState, WeatherMainSideEffect>(
    scope = scope,
    initialState = WeatherMainState()
) {

    fun loadWeather(
        lat: Double, lon: Double
    ) {
        storeScope.launch {
            setState { copy(loadState = LoadState.Loading) }

            try {
                val deferredCurrentWeather = async {
                    getCurrentWeatherUseCase(
                        GetCurrentWeatherUseCase.GetCurrentWeatherParams(
                            lat = lat,
                            lon = lon,
                            units = WeatherUnit.METRIC.unitName,
                            language = Language.KR.languageName,
                        )
                    ).first().getOrThrow()
                }

                val deferredWeatherFor5Days = async {
                    getWeathersFor5DaysUseCase(
                        GetWeathersFor5DaysUseCase.GetWeathersFor5DaysParams(
                            lat = lat,
                            lon = lon,
                            units = WeatherUnit.METRIC.unitName,
                            mode = emptyList(),
                            lang = Language.KR.languageName,
                        )
                    ).first().getOrThrow()
                }

                val currentWeather = deferredCurrentWeather.await()
                val weatherFor5Days = deferredWeatherFor5Days.await()

                setState {
                    copy(
                        loadState = LoadState.Success,
                        weather = currentWeatherMapper.mapToPresentation(currentWeather),
                        weatherFor5days = weatherFor5DaysMapper.mapToPresentation(weatherFor5Days)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                setState {
                    copy(
                        loadState = LoadState.Error(
                            e.message ?: "WeatherMainStore: Unknown error - loadWeather"
                        )
                    )
                }
            }
        }
    }
}