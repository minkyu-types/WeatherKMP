package org.dosys.project.presentation.feature.weather.main

import org.dosys.weather_domain.model.Wind

data class WeathersFor5DaysModel(
    val cnt: Int,
    val list: List<DailyWeatherModel>,
    val city: CityModel
)

data class DailyWeatherModel(
    val dt: Long,
    val main: MainModel,
    val weather: WeatherModel,
    val clouds: CloudsModel,
    val wind: WindModel,
    val visibility: Int,
    val pop: Double,
    val rain: RainModel,
    val sys: SysModel,
    val dtText: String
) {

    data class SysModel(
        val pod: String
    )
}

data class CityModel(
    val id: Int,
    val name: String,
    val coord: CoordModel,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
)