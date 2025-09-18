package org.dosys.project.presentation.feature.weather.main

import com.samsung.weather_data.remote.model.type.WeatherUnit.TemperatureUnit

data class CurrentWeatherModel(
    val coord: CoordModel,
    val weather: List<WeatherModel>,
    val base: String,
    val main: MainModel,
    val visibility: Int,
    val wind: WindModel,
    val rain: RainModel? = null,
    val clouds: CloudsModel,
    val dt: Long,
    val sys: SysModel,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

data class CoordModel(
    val lon: Double,
    val lat: Double
)

data class WeatherModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainModel (
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val grndLevel: Int
)

data class WindModel(
    val speed: Double,
    val deg: Int,
    val gust: Double? = null
)

data class RainModel(
    val oneHour: Double? = null
)

data class CloudsModel(
    val all: Int
)

data class SysModel(
    val type: Int? = null,
    val id: Int? = null,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

fun Double.convert(from: TemperatureUnit, to: TemperatureUnit): Double {
    if (from == to) return this
    return when (from to to) {
        TemperatureUnit.CELSIUS to TemperatureUnit.FAHRENHEIT    -> this * 9.0 / 5.0 + 32.0
        TemperatureUnit.FAHRENHEIT to TemperatureUnit.CELSIUS    -> (this - 32.0) * 5.0 / 9.0
        TemperatureUnit.KELVIN to TemperatureUnit.CELSIUS        -> this - 273.15
        TemperatureUnit.CELSIUS to TemperatureUnit.KELVIN        -> this + 273.15
        TemperatureUnit.KELVIN to TemperatureUnit.FAHRENHEIT     -> (this - 273.15) * 9.0 / 5.0 + 32.0
        TemperatureUnit.FAHRENHEIT to TemperatureUnit.KELVIN     -> (this - 32.0) * 5.0 / 9.0 + 273.15
        else -> error("Unsupported conversion")
    }
}