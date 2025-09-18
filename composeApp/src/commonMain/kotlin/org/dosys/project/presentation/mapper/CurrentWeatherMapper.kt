package org.dosys.project.presentation.mapper

import org.dosys.project.presentation.feature.weather.main.CloudsModel
import org.dosys.project.presentation.feature.weather.main.CoordModel
import org.dosys.project.presentation.feature.weather.main.CurrentWeatherModel
import org.dosys.project.presentation.feature.weather.main.MainModel
import org.dosys.project.presentation.feature.weather.main.SysModel
import org.dosys.project.presentation.feature.weather.main.WeatherModel
import org.dosys.project.presentation.feature.weather.main.WindModel
import org.dosys.weather_domain.model.Clouds
import org.dosys.weather_domain.model.Coord
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.Main
import org.dosys.weather_domain.model.Sys
import org.dosys.weather_domain.model.Weather
import org.dosys.weather_domain.model.Wind

class CurrentWeatherMapper {

    fun mapToDomain(
        currentWeatherModel: CurrentWeatherModel
    ): CurrentWeather {
        with (currentWeatherModel) {
            return CurrentWeather(
                coord = Coord(
                    lon = currentWeatherModel.coord.lon,
                    lat = currentWeatherModel.coord.lat
                ),
                weather = currentWeatherModel.weather.map {
                    Weather(
                        id = it.id,
                        main = it.main,
                        description = it.description,
                        icon = it.icon
                    )
                },
                base = currentWeatherModel.base,
                main = Main(
                    temp = currentWeatherModel.main.temp,
                    feelsLike = currentWeatherModel.main.feelsLike,
                    tempMin = currentWeatherModel.main.tempMin,
                    tempMax = currentWeatherModel.main.tempMax,
                    pressure = currentWeatherModel.main.pressure,
                    humidity = currentWeatherModel.main.humidity,
                    seaLevel = currentWeatherModel.main.seaLevel,
                    groundLevel = currentWeatherModel.main.grndLevel
                ),
                visibility = currentWeatherModel.visibility,
                wind = Wind(
                    speed = currentWeatherModel.wind.speed,
                    deg = currentWeatherModel.wind.deg,
                    gust = currentWeatherModel.wind.gust
                ),
                rain = null,
                clouds = Clouds(
                    all = currentWeatherModel.clouds.all
                ),
                dt = currentWeatherModel.dt,
                sys = Sys(
                    type = currentWeatherModel.sys.type,
                    id = currentWeatherModel.sys.id,
                    country = currentWeatherModel.sys.country,
                    sunrise = currentWeatherModel.sys.sunrise,
                    sunset = currentWeatherModel.sys.sunset
                ),
                timezone = currentWeatherModel.timezone,
                id = currentWeatherModel.id,
                name = currentWeatherModel.name,
                cod = currentWeatherModel.cod
            )
        }
    }

    fun mapToPresentation(
        currentWeather: CurrentWeather
    ): CurrentWeatherModel {
        with (currentWeather) {
            return CurrentWeatherModel(
                coord = CoordModel(
                    lon = currentWeather.coord.lon,
                    lat = currentWeather.coord.lat
                ),
                weather = currentWeather.weather.map {
                    WeatherModel(
                        id = it.id,
                        main = it.main,
                        description = it.description,
                        icon = it.icon
                    )
                },
                base = currentWeather.base,
                main = MainModel(
                    temp = currentWeather.main.temp,
                    feelsLike = currentWeather.main.feelsLike,
                    tempMin = currentWeather.main.tempMin,
                    tempMax = currentWeather.main.tempMax,
                    pressure = currentWeather.main.pressure,
                    humidity = currentWeather.main.humidity,
                    seaLevel = currentWeather.main.seaLevel,
                    grndLevel = currentWeather.main.groundLevel
                ),
                visibility = currentWeather.visibility,
                wind = WindModel(
                    speed = currentWeather.wind.speed,
                    deg = currentWeather.wind.deg,
                    gust = currentWeather.wind.gust
                ),
                rain = null,
                clouds = CloudsModel(
                    all = currentWeather.clouds.all
                ),
                dt = currentWeather.dt,
                sys = SysModel(
                    type = currentWeather.sys.type,
                    id = currentWeather.sys.id,
                    country = currentWeather.sys.country,
                    sunrise = currentWeather.sys.sunrise,
                    sunset = currentWeather.sys.sunset
                ),
                timezone = currentWeather.timezone,
                id = currentWeather.id,
                name = currentWeather.name,
                cod = currentWeather.cod
            )
        }
    }

    private fun WeatherModel.toDomain(): Weather =
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
}