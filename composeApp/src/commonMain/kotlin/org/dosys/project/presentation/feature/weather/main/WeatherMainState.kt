package org.dosys.project.presentation.feature.weather.main

import org.dosys.project.presentation.feature.base.BaseState
import org.dosys.project.presentation.feature.base.LoadState

data class WeatherMainState(
    override val loadState: LoadState = LoadState.Idle,
    val weather: Weather? = null,
    val currentListType: WeatherListType? = WeatherListType.HOURLY,
): BaseState {

    enum class WeatherListType {
        CURRENT,
        MINUTELY,
        HOURLY,
        DAILY,
    }
}