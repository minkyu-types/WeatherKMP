package org.dosys.project.util

import com.samsung.weather_data.remote.model.type.WeatherUnit.TemperatureUnit

object TemperatureConverter {

    fun convert(temp: Double, from: TemperatureUnit, to: TemperatureUnit): Double {
        if (from == to) return temp
        return when (from to to) {
            TemperatureUnit.CELSIUS to TemperatureUnit.FAHRENHEIT    -> temp * 9.0 / 5.0 + 32.0
            TemperatureUnit.FAHRENHEIT to TemperatureUnit.CELSIUS    -> (temp - 32.0) * 5.0 / 9.0
            TemperatureUnit.KELVIN to TemperatureUnit.CELSIUS        -> temp - 273.15
            TemperatureUnit.CELSIUS to TemperatureUnit.KELVIN        -> temp + 273.15
            TemperatureUnit.KELVIN to TemperatureUnit.FAHRENHEIT     -> (temp - 273.15) * 9.0 / 5.0 + 32.0
            TemperatureUnit.FAHRENHEIT to TemperatureUnit.KELVIN     -> (temp - 32.0) * 5.0 / 9.0 + 273.15
            else -> error("Unsupported conversion")
        }
    }
}