package org.dosys.project.presentation.feature.weather.main

import kotlinx.datetime.LocalDate

data class DailyWeatherModel(
    val dt: LocalDate,
    val weather: WeatherModel,
    val pop: Double,
    val rain: DailyRainModel? = null,
    val temp: TempModel,
) {
    data class DailyRainModel(
        val amount: Double
    )

    data class TempModel(
        val curr: Double,
        val min: Double,
        val max: Double
    )
}
