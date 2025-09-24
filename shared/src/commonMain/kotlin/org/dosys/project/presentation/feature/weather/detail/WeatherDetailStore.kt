package org.dosys.project.presentation.feature.weather.detail

import kotlinx.coroutines.CoroutineScope
import org.dosys.project.presentation.feature.base.BaseStore
import org.dosys.weather_domain.repository.WeatherRepository

class WeatherDetailStore(
    private val weatherRepository: WeatherRepository,
    scope: CoroutineScope,
): BaseStore<WeatherDetailState, WeatherDetailSideEffect>(
    scope = scope,
    initialState = WeatherDetailState()
) {
}