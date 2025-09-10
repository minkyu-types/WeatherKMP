package org.dosys.project.presentation.feature.weather.main

import org.dosys.weather_domain.model.Location

data class CurrentWeatherModel(
    val location: Location,
    val timezone: String,
    val timezoneOffset: Int,
    val current: Current,
    val minutely: List<Minutely> = emptyList(),
    val hourly: List<Hourly> = emptyList(),
    val daily: List<Daily> = emptyList(),
    val alerts: List<Alert> = emptyList()
)

data class Current(
    val dt: Long,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int? = null,
    val windSpeed: Double,
    val windDeg: Int,
    val windGust: Double? = null,
    val weather: List<Weather>
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Minutely(
    val dt: Long,
    val precipitation: Double
)

data class Hourly(
    val dt: Long,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDeg: Int,
    val windGust: Double? = null,
    val weather: List<Weather>,
    val pop: Double
)

data class Daily(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moonPhase: Double,
    val summary: String? = null,
    val temp: Temp,
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val windSpeed: Double,
    val windDeg: Int,
    val windGust: Double? = null,
    val weather: List<Weather>,
    val clouds: Int,
    val pop: Double,
    val rain: Double? = null,
    val uvi: Double
)

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class Alert(
    val senderName: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String,
    val tags: List<String> = emptyList()
)
