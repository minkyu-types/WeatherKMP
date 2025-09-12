package com.samsung.weather_data.remote.mapper

import com.samsung.weather_data.remote.model.AlertDto
import com.samsung.weather_data.remote.model.CurrentDto
import com.samsung.weather_data.remote.model.DailyDto
import com.samsung.weather_data.remote.model.FeelsLikeDto
import com.samsung.weather_data.remote.model.GetCurrentAndForecastsWeatherDto
import com.samsung.weather_data.remote.model.HourlyDto
import com.samsung.weather_data.remote.model.MinutelyDto
import com.samsung.weather_data.remote.model.TempDto
import com.samsung.weather_data.remote.model.WeatherDto
import org.dosys.weather_domain.model.Alert
import org.dosys.weather_domain.model.Current
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.Daily
import org.dosys.weather_domain.model.FeelsLike
import org.dosys.weather_domain.model.Hourly
import org.dosys.weather_domain.model.Location
import org.dosys.weather_domain.model.Minutely
import org.dosys.weather_domain.model.Temp
import org.dosys.weather_domain.model.Weather

class CurrentAndForecastsWeatherMapper {

    fun mapToDomain(
        currentWeatherModel: GetCurrentAndForecastsWeatherDto
    ): CurrentWeather {
        with (currentWeatherModel) {
            return CurrentWeather(
                location = Location(latitude = latitude, longitude = longitude),
                timezone = timezone,
                timezoneOffset = timezoneOffset,
                current = current.toDomain(),
                minutely = minutely.map { it.toDomain() },
                hourly = hourly.map { it.toDomain() },
                daily = daily.map { it.toDomain() },
                alerts = alerts.map { it.toDomain() }
            )
        }
    }

    fun mapToData(
        currentWeather: CurrentWeather
    ): GetCurrentAndForecastsWeatherDto {
        with (currentWeather) {
            return GetCurrentAndForecastsWeatherDto(
                latitude = location.latitude,
                longitude = location.longitude,
                timezone = timezone,
                timezoneOffset = timezoneOffset,
                current = current.toPresentation(),
                minutely = minutely.map { it.toData() },
                hourly = hourly.map { it.toData() },
                daily = daily.map { it.toData() },
                alerts = alerts.map { it.toData() }
            )
        }
    }

    private fun CurrentDto.toDomain(): Current =
        Current(
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
            windGust = windGust,
            weather = weather.map { it.toDomain() }
        )

    private fun WeatherDto.toDomain(): Weather =
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )

    private fun MinutelyDto.toDomain(): Minutely =
        Minutely(
            dt = dt,
            precipitation = precipitation
        )

    private fun HourlyDto.toDomain(): Hourly =
        Hourly(
            dt = dt,
            temp = temp,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            uvi = uvi ?: 0.0,
            clouds = clouds,
            visibility = visibility,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            weather = weather.map { it.toDomain() },
            pop = pop
        )

    private fun DailyDto.toDomain(): Daily =
        Daily(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            moonrise = moonrise,
            moonset = moonset,
            moonPhase = moonPhase,
            summary = summary,
            temp = temp.toDomain(),
            feelsLike = feelsLike.toDomain(),
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            weather = weather.map { it.toDomain() },
            clouds = clouds,
            pop = pop,
            rain = rain,
            uvi = uvi
        )

    private fun TempDto.toDomain(): Temp =
        Temp(
            day = day,
            min = min,
            max = max,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun FeelsLikeDto.toDomain(): FeelsLike =
        FeelsLike(
            day = day,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun AlertDto.toDomain(): Alert =
        Alert(
            senderName = senderName,
            event = event,
            start = start,
            end = end,
            description = description,
            tags = tags
        )

    private fun Current.toPresentation(): CurrentDto =
        CurrentDto(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            temp = temp,
            feelsLike = feelsLike,
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            uvi = uvi,                         // 도메인은 non-null
            clouds = clouds,
            visibility = visibility,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            weather = weather.map { it.toData() }
        )

    private fun Weather.toData(): WeatherDto =
        WeatherDto(
            id = id,
            main = main,
            description = description,
            icon = icon
        )

    private fun Minutely.toData(): MinutelyDto =
        MinutelyDto(
            dt = dt,
            precipitation = precipitation
        )

    private fun Hourly.toData(): HourlyDto =
        HourlyDto(
            dt = dt,
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
            windGust = windGust,
            weather = weather.map { it.toData() },
            pop = pop
        )

    private fun Daily.toData(): DailyDto =
        DailyDto(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            moonrise = moonrise,
            moonset = moonset,
            moonPhase = moonPhase,
            summary = summary,
            temp = temp.toData(),
            feelsLike = feelsLike.toData(),
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            weather = weather.map { it.toData() },
            clouds = clouds,
            pop = pop,
            rain = rain,
            uvi = uvi
        )

    private fun Temp.toData(): TempDto=
        TempDto(
            day = day,
            min = min,
            max = max,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun FeelsLike.toData(): FeelsLikeDto =
        FeelsLikeDto(
            day = day,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun Alert.toData(): AlertDto =
        AlertDto(
            senderName = senderName,
            event = event,
            start = start,
            end = end,
            description = description,
            tags = tags
        )
}