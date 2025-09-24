package org.dosys.project.presentation.feature.weather.detail

import org.dosys.project.presentation.feature.base.BaseSideEffect
import org.dosys.project.presentation.feature.weather.main.WeatherModel

sealed class WeatherDetailSideEffect: BaseSideEffect {
    data class Error(val message: String): WeatherDetailSideEffect()
    data class Success(val weatherModel: WeatherModel): WeatherDetailSideEffect()
    data class ShowAlert(val message: String): WeatherDetailSideEffect()

}