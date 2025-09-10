package org.dosys.project.presentation.feature.weather.main

import kotlinx.coroutines.CoroutineScope
import org.dosys.project.presentation.feature.base.BaseStore
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.model.Location
import org.dosys.weather_domain.repository.WeatherRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class WeatherMainStore(
    private val weatherRepository: WeatherRepository,
    scope: CoroutineScope,
): BaseStore<WeatherMainState, WeatherMainSideEffect>(
    scope = scope,
    initialState = WeatherMainState()
) {

}