package org.dosys.weather_domain.model

data class DailyAggregation(
    val location: Location,
    val tz: String,
    val date: String,
    val units: String,
    val cloudCover: CloudCover,
    val humidity: Humidity,
    val precipitation: Precipitation,
    val temperature: Temperature,
    val pressure: Pressure,
    val wind: Wind
)

data class CloudCover(
    val afternoon: Int
)

data class Humidity(
    val afternoon: Int
)

data class Precipitation(
    val total: Int
)

data class Temperature(
    val min: Double,
    val max: Double,
    val afternoon: Double,
    val night: Double,
    val evening: Double,
    val morning: Double
)

data class Pressure(
    val afternoon: Int
)

data class Wind(
    val max: WindMax
)

data class WindMax(
    val speed: Double,
    val direction: Int
)
