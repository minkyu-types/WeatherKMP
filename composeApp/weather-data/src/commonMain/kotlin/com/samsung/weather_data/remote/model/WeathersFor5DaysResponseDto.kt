package com.samsung.weather_data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeathersFor5DaysResponseDto(
    val cod: Int,
    val message: String,
    val cnt: Int,
    val list: List<DailyWeatherDto>,
    val city: CityDto
)

@Serializable
data class DailyWeatherDto(
    val dt: Long,
    val main: MainDto,
    val weather: WeatherDto,
    val clouds: CloudsDto,
    val wind: WindDto,
    val visibility: Int,
    val pop: Double,
    val rain: RainDto,
    val sys: Sys,
    @SerialName("dt_txt") val dtText: String
) {

    @Serializable
    data class Sys(
        val pod: String
    )
}

@Serializable
data class CityDto(
    val id: Int,
    val name: String,
    val coord: CoordDto,
    @SerialName("country") val countryCode: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
)