package org.dosys.project.presentation.feature.weather.main

import org.dosys.project.presentation.feature.base.BaseSideEffect

sealed class WeatherMainSideEffect: BaseSideEffect {
    data class ShowAlert(val message: String): WeatherMainSideEffect()
    data class ChangeListType(val listType: WeatherMainState.WeatherListType): WeatherMainSideEffect()
}