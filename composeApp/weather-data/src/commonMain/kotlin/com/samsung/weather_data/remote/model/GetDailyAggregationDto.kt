package com.samsung.weather_data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDailyAggregationDto(
    val lat: Double,
    val lon: Double,
    val tz: String,
    val date: String,
    val units: String,
    @SerialName("cloud_cover") val cloudCover: com.samsung.weather_data.remote.model.CloudCoverDto,
    val humidity: com.samsung.weather_data.remote.model.HumidityDto,
    val precipitation: com.samsung.weather_data.remote.model.PrecipitationDto,
    val temperature: com.samsung.weather_data.remote.model.TemperatureDto,
    val pressure: com.samsung.weather_data.remote.model.PressureDto,
    val wind: com.samsung.weather_data.remote.model.WindDto
)

@Serializable
data class CloudCoverDto(
    val afternoon: Int
)

@Serializable
data class HumidityDto(
    val afternoon: Int
)

@Serializable
data class PrecipitationDto(
    val total: Int
)

@Serializable
data class TemperatureDto(
    val min: Double,
    val max: Double,
    val afternoon: Double,
    val night: Double,
    val evening: Double,
    val morning: Double
)

@Serializable
data class PressureDto(
    val afternoon: Int
)

@Serializable
data class WindDto(
    val max: com.samsung.weather_data.remote.model.WindMaxDto
)

@Serializable
data class WindMaxDto(
    val speed: Double,
    val direction: Int
)
