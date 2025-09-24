package org.dosys.project.presentation.feature.weather.main

data class WeathersFor5DaysModel(
    val cnt: Int,
    val list: List<ThreeHourForecastModel>,
    val city: CityModel
)

data class ThreeHourForecastModel(
    val dt: Long,
    val main: MainModel,
    val weather: List<WeatherModel>,
    val clouds: CloudsModel,
    val wind: WindModel,
    val visibility: Int,
    val pop: Double,
    val rain: RainThreeHourModel?,
    val sys: SysModel,
    val dtText: String
) {

    data class SysModel(
        val pod: String
    )

    data class RainThreeHourModel(
        val threeHour: Double
    )
}

data class CityModel(
    val id: Int,
    val name: String,
    val coord: CoordModel,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
)