package com.samsung.weather_data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWeatherOverviewDto(
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    @SerialName("tz") val timezone: String,
    val date: String,
    val units: String,
    @SerialName("weather_overview") val weatherOverview: String
)
