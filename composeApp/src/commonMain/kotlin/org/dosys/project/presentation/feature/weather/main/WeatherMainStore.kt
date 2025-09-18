package org.dosys.project.presentation.feature.weather.main

import com.samsung.weather_data.remote.model.type.Language
import com.samsung.weather_data.remote.model.type.WeatherUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.dosys.project.presentation.feature.base.BaseStore
import org.dosys.project.presentation.feature.base.LoadState
import org.dosys.project.presentation.mapper.CurrentWeatherMapper
import org.dosys.project.presentation.mapper.WeatherFor5DaysMapper
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

    fun getCurrentWeather(
        lat: Double, lon: Double,
    ) {
        storeScope.launch {
            setState { copy(loadState = LoadState.Loading) }

            try {
                getCurrentWeatherUseCase(
                    GetCurrentWeatherUseCase.GetCurrentWeatherParams(
                        lat = lat,
                        lon = lon,
                        units = WeatherUnit.METRIC.unitName,
                        language = Language.KR.languageName,
                    )
                ).collect { result ->
                    setState {
                        copy(
                            loadState = LoadState.Success,
                            weather = currentWeatherMapper.mapToPresentation(result)
                        )
                    }
                }
            } catch (e: Exception) {
                setState { copy(loadState = LoadState.Error(e.message ?: "WeatherMainStore: Unknown error")) }
            }
        }
    }
}