package com.samsung.weather_data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWeatherByTimeStampDto(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerialName("timezone_offset") val timezoneOffset: Int,
    val data: List<DailyDataDto>
)

@Serializable
data class DailyDataDto(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerialName("dew_point") val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Int,
    val weather: List<WeatherDto>
)