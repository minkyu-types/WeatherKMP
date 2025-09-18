package com.samsung.weather_data.remote.model.type

enum class WeatherUnit(
    val unitName: String,
    temperatureUnit: TemperatureUnit,
    windSpeedUnit: WindSpeedUnit,
    pressureUnit: PressureUnit,
    rainUnit: RainUnit
) {
    STANDARD("standard",
        TemperatureUnit.KELVIN,
        WindSpeedUnit.METRIC,
        PressureUnit.HPA,
        RainUnit.MM
    ),
    METRIC("metric",
        TemperatureUnit.CELSIUS,
        WindSpeedUnit.METRIC,
        PressureUnit.HPA,
        RainUnit.MM
    ),
    IMPERIAL("imperial",
        TemperatureUnit.FAHRENHEIT,
        WindSpeedUnit.IMPERIAL,
        PressureUnit.HPA,
        RainUnit.MM
    );

    companion object {
        val DEFAULT = STANDARD
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