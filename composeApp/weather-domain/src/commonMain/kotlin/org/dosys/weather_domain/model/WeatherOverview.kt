package org.dosys.weather_domain.model

data class WeatherOverview(
    val location: Location,
    val timezone: String,
    val date: String,
    val units: String,
    val weatherOverview: String
)