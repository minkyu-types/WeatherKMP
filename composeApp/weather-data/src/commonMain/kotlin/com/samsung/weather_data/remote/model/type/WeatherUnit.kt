package com.samsung.weather_data.remote.model.type

enum class WeatherUnit(
    unitName: String,
    temperatureUnit: com.samsung.weather_data.remote.model.type.WeatherUnit.TemperatureUnit,
    windSpeedUnit: com.samsung.weather_data.remote.model.type.WeatherUnit.WindSpeedUnit,
    pressureUnit: com.samsung.weather_data.remote.model.type.WeatherUnit.PressureUnit,
    rainUnit: com.samsung.weather_data.remote.model.type.WeatherUnit.RainUnit
) {
    STANDARD("standard",
        com.samsung.weather_data.remote.model.type.WeatherUnit.TemperatureUnit.KELVIN,
        com.samsung.weather_data.remote.model.type.WeatherUnit.WindSpeedUnit.METRIC,
        com.samsung.weather_data.remote.model.type.WeatherUnit.PressureUnit.HPA,
        com.samsung.weather_data.remote.model.type.WeatherUnit.RainUnit.MM
    ),
    METRIC("metric",
        com.samsung.weather_data.remote.model.type.WeatherUnit.TemperatureUnit.CELSIUS,
        com.samsung.weather_data.remote.model.type.WeatherUnit.WindSpeedUnit.METRIC,
        com.samsung.weather_data.remote.model.type.WeatherUnit.PressureUnit.HPA,
        com.samsung.weather_data.remote.model.type.WeatherUnit.RainUnit.MM
    ),
    IMPERIAL("imperial",
        com.samsung.weather_data.remote.model.type.WeatherUnit.TemperatureUnit.FAHRENHEIT,
        com.samsung.weather_data.remote.model.type.WeatherUnit.WindSpeedUnit.IMPERIAL,
        com.samsung.weather_data.remote.model.type.WeatherUnit.PressureUnit.HPA,
        com.samsung.weather_data.remote.model.type.WeatherUnit.RainUnit.MM
    );

    companion object {
        val DEFAULT = com.samsung.weather_data.remote.model.type.WeatherUnit.STANDARD
    }

    enum class TemperatureUnit(
        val notation: String
    ) {
        KELVIN("°C"),
        CELSIUS("K"),
        FAHRENHEIT("°F")
    }

    enum class WindSpeedUnit(
        val notation: String
    ) {
        METRIC("m/s"),
        IMPERIAL("mph")
    }

    enum class PressureUnit(
        val notation: String
    ) {
        HPA("hPa")
    }

    enum class RainUnit(
        val notation: String
    ) {
        MM("mm")
    }
}