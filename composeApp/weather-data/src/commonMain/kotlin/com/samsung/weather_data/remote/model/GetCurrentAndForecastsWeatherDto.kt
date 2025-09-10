package com.samsung.weather_data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GetCurrentAndForecastsWeatherDto(
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    val timezone: String,
    @SerialName("timezone_offset") val timezoneOffset: Int,
    val current: CurrentDto,
    val minutely: List<MinutelyDto> = emptyList(),
    val hourly: List<HourlyDto> = emptyList(),
    val daily: List<DailyDto> = emptyList(),
    val alerts: List<AlertDto> = emptyList()
)

@kotlinx.serialization.Serializable
data class CurrentDto(
    val dt: Long,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerialName("dew_point") val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int? = null,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Int,
    @SerialName("wind_gust") val windGust: Double? = null,
    val weather: List<WeatherDto>
)

@kotlinx.serialization.Serializable
data class MinutelyDto(
    val dt: Long,
    val precipitation: Double
)

@kotlinx.serialization.Serializable
data class HourlyDto(
    val dt: Long,
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
    @SerialName("wind_gust") val windGust: Double? = null,
    val weather: List<WeatherDto>,
    val pop: Double
)

@kotlinx.serialization.Serializable
data class DailyDto(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    @SerialName("moon_phase") val moonPhase: Double,
    val summary: String? = null,
    val temp: TempDto,
    @SerialName("feels_like") val feelsLike: FeelsLikeDto,
    val pressure: Int,
    val humidity: Int,
    @SerialName("dew_point") val dewPoint: Double,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Int,
    @SerialName("wind_gust") val windGust: Double? = null,
    val weather: List<WeatherDto>,
    val clouds: Int,
    val pop: Double,
    val rain: Double? = null,
    val uvi: Double
)

@kotlinx.serialization.Serializable
data class TempDto(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

@kotlinx.serialization.Serializable
data class FeelsLikeDto(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

@kotlinx.serialization.Serializable
data class WeatherDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@kotlinx.serialization.Serializable
data class AlertDto(
    @SerialName("sender_name") val senderName: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String,
    val tags: List<String> = emptyList()
)

