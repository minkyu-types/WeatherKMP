package org.dosys.project.common

import kotlinx.coroutines.flow.Flow
import org.dosys.project.data.model.CurrentAndForecastsWeatherDto

actual class WeatherController {
    actual fun getWeatherFlow(
        lat: Double,
        long: Double
    ): Flow<CurrentAndForecastsWeatherDto> {
        TODO("Not yet implemented")
    }

}