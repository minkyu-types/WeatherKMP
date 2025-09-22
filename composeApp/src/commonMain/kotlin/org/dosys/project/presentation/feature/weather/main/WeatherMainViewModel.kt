package org.dosys.project.presentation.feature.weather.main

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.dosys.project.presentation.mapper.CurrentWeatherMapper
import org.dosys.project.util.SharedViewModel

class WeatherMainViewModel(
    factory: WeatherMainStoreFactory,
    currentWeatherMapper: CurrentWeatherMapper
) : SharedViewModel() {

    private val store = factory.create(viewModelScope)

    val state: StateFlow<WeatherMainState> = store.state
    val effect: Flow<WeatherMainSideEffect> = store.effect

    init {
        store.loadWeather(33.44, -94.04)
    }
}