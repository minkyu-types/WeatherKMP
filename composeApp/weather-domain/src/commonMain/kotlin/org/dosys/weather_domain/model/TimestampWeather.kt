package org.dosys.weather_domain.model

data class TimestampWeather(
    val location: Location,
    val timezone: String,
    val timezoneOffset: Int,
    val data: List<DailyData>
)

data class DailyData(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
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
    val weather: List<Weather>
)