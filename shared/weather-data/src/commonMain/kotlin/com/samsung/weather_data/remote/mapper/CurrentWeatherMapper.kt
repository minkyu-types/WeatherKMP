package com.samsung.weather_data.remote.mapper

import com.samsung.weather_data.remote.model.CloudsDto
import com.samsung.weather_data.remote.model.CoordDto
import com.samsung.weather_data.remote.model.CurrentWeatherResponseDto
import com.samsung.weather_data.remote.model.MainDto
import com.samsung.weather_data.remote.model.RainDto
import com.samsung.weather_data.remote.model.SysDto
import com.samsung.weather_data.remote.model.WeatherDto
import com.samsung.weather_data.remote.model.WindDto
import org.dosys.weather_domain.model.Clouds
import org.dosys.weather_domain.model.Coord
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.Main
import org.dosys.weather_domain.model.Rain
import org.dosys.weather_domain.model.Sys
import org.dosys.weather_domain.model.Weather
import org.dosys.weather_domain.model.Wind

internal class CurrentWeatherMapper {

    fun mapToDomain(dto: CurrentWeatherResponseDto): CurrentWeather {
        return CurrentWeather(
            coord = Coord(dto.coord.lon, dto.coord.lat),
            weather = dto.weather.map {
                Weather(it.id, it.main, it.description, it.icon)
            },
            base = dto.base,
            main = Main(
                temp = dto.main.temp,
                feelsLike = dto.main.feelsLike,
                tempMin = dto.main.tempMin,
                tempMax = dto.main.tempMax,
                pressure = dto.main.pressure,
                humidity = dto.main.humidity,
                seaLevel = dto.main.seaLevel,
                groundLevel = dto.main.groundLevel
            ),
            visibility = dto.visibility,
            wind = Wind(dto.wind.speed, dto.wind.deg, dto.wind.gust),
            rain = dto.rain?.let { Rain(it.oneHour) },
            clouds = Clouds(dto.clouds.all),
            dt = dto.dt,
            sys = Sys(
                type = dto.sys.type,
                id = dto.sys.id,
                country = dto.sys.country,
                sunrise = dto.sys.sunrise,
                sunset = dto.sys.sunset
            ),
            timezone = dto.timezone,
            id = dto.id,
            name = dto.name,
            cod = dto.cod
        )
    }

    fun mapToData(
        currentWeather: CurrentWeather
    ): CurrentWeatherResponseDto {
        with (currentWeather) {
            return CurrentWeatherResponseDto(
                coord = CoordDto(coord.lon, coord.lat),
                weather = weather.map {
                    WeatherDto(it.id, it.main, it.description, it.icon)
                },
                base = base,
                main = MainDto(
                    temp = currentWeather.main.temp,
                    feelsLike = currentWeather.main.feelsLike,
                    tempMin = currentWeather.main.tempMin,
                    tempMax = currentWeather.main.tempMax,
                    pressure = currentWeather.main.pressure,
                    humidity = currentWeather.main.humidity,
                ),
                visibility = currentWeather.visibility,
                wind = WindDto(currentWeather.wind.speed, currentWeather.wind.deg, currentWeather.wind.gust),
                rain = currentWeather.rain?.let { RainDto(it.oneHour) },
                clouds = CloudsDto(currentWeather.clouds.all),
                dt = currentWeather.dt,
                sys = SysDto(
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

    private fun WeatherDto.toDomain(): Weather =
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )


}