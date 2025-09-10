package org.dosys.weather_domain.model

enum class ExcludeType(val value: String) {
    CURRENT("current"),
    MINUTELY("minutely"),
    HOURLY("hourly"),
    DAILY("daily"),
    ALERTS("alerts"),
}