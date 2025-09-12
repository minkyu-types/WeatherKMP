package org.dosys.project.presentation.mapper

import com.samsung.weather_data.remote.model.DailyDataDto
import com.samsung.weather_data.remote.model.GetWeatherByTimeStampDto
import com.samsung.weather_data.remote.model.WeatherDto
import org.dosys.weather_domain.model.DailyData
import org.dosys.weather_domain.model.Location
import org.dosys.weather_domain.model.TimestampWeather
import org.dosys.weather_domain.model.Weather

class WeatherByTimestampMapper {

    fun mapToDomain(
        getWeatherByTimeStampDto: GetWeatherByTimeStampDto
    ): TimestampWeather {
        with(getWeatherByTimeStampDto) {
            return  TimestampWeather(
                location = Location(latitude = lat, longitude = lon),
                timezone = timezone,
                timezoneOffset = timezoneOffset,
                data = data.map { it.toDomain() }
            )
        }
    }

    fun mapToData(
        timestampWeather: TimestampWeather
    ): GetWeatherByTimeStampDto {
        with(timestampWeather) {
            return GetWeatherByTimeStampDto(
                lat = location.latitude,
                lon = location.longitude,
                timezone = timezone,
                timezoneOffset = timezoneOffset,
                data = data.map { it.toData() }
            )
        }
    }

    private fun DailyDataDto.toDomain(): DailyData =
        DailyData(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            temp = temp,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            uvi = uvi,
            clouds = clouds,
            visibility = visibility,
            windSpeed = windSpeed,
            windDeg = windDeg,
            weather = weather.map { it.toDomain() }
        )

    private fun WeatherDto.toDomain(): Weather =
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )

    private fun DailyData.toData(): DailyDataDto =
        DailyDataDto(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            temp = temp,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            uvi = uvi,
            clouds = clouds,
            visibility = visibility,
            windSpeed = windSpeed,
            windDeg = windDeg,
            weather = weather.map { it.toData() }
        )

    private fun Weather.toData(): WeatherDto =
        WeatherDto(
            id = id,
            main = main,
            description = description,
            icon = icon
        )
}