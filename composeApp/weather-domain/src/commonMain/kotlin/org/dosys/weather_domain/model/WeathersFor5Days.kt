package org.dosys.weather_domain.model

data class WeathersFor5Days(
    val cnt: Int,
    val list: List<DailyWeather>,
    val city: City
)

data class DailyWeather(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val rain: Rain3Hour? = null,
    val sys: Sys,
    val dtText: String
) {

    data class Sys(
        val pod: String
    )

    data class Rain3Hour(
        val threeHour: Double
    )
}

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
)