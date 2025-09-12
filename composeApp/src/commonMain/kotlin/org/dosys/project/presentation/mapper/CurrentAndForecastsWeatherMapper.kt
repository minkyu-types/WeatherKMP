package org.dosys.project.presentation.mapper

import org.dosys.project.presentation.feature.weather.main.AlertModel
import org.dosys.project.presentation.feature.weather.main.CurrentModel
import org.dosys.project.presentation.feature.weather.main.CurrentWeatherModel
import org.dosys.project.presentation.feature.weather.main.DailyModel
import org.dosys.project.presentation.feature.weather.main.FeelsLikeModel
import org.dosys.project.presentation.feature.weather.main.HourlyModel
import org.dosys.project.presentation.feature.weather.main.MinutelyModel
import org.dosys.project.presentation.feature.weather.main.TempModel
import org.dosys.project.presentation.feature.weather.main.WeatherModel
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
        currentWeatherModel: CurrentWeatherModel
    ): CurrentWeather {
        with (currentWeatherModel) {
            return CurrentWeather(
                location = Location(latitude = location.latitude, longitude = location.longitude),
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

    fun mapToPresentation(
        currentWeather: CurrentWeather
    ): CurrentWeatherModel {
        with (currentWeather) {
            return CurrentWeatherModel(
                location = location,
                timezone = timezone,
                timezoneOffset = timezoneOffset,
                current = current.toPresentation(),
                minutely = minutely.map { it.toPresentation() },
                hourly = hourly.map { it.toPresentation() },
                daily = daily.map { it.toPresentation() },
                alerts = alerts.map { it.toPresentation() }
            )
        }
    }

    private fun CurrentModel.toDomain(): Current =
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

    private fun WeatherModel.toDomain(): Weather =
        Weather(
            id = id,
            main = main,
            description = description,
            icon = icon
        )

    private fun MinutelyModel.toDomain(): Minutely =
        Minutely(
            dt = dt,
            precipitation = precipitation
        )

    private fun HourlyModel.toDomain(): Hourly =
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

    private fun DailyModel.toDomain(): Daily =
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
            weather = (weather ?: emptyList()).map { it.toDomain() },
            clouds = clouds,
            pop = pop,
            rain = rain,
            uvi = uvi
        )

    private fun TempModel.toDomain(): Temp =
        Temp(
            day = day,
            min = min,
            max = max,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun FeelsLikeModel.toDomain(): FeelsLike =
        FeelsLike(
            day = day,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun AlertModel.toDomain(): Alert =
        Alert(
            senderName = senderName,
            event = event,
            start = start,
            end = end,
            description = description,
            tags = tags
        )

    private fun Current.toPresentation(): CurrentModel =
        CurrentModel(
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
            weather = weather.map { it.toPresentation() }
        )

    private fun Weather.toPresentation(): WeatherModel =
        WeatherModel(
            id = id,
            main = main,
            description = description,
            icon = icon
        )

    private fun Minutely.toPresentation(): MinutelyModel =
        MinutelyModel(
            dt = dt,
            precipitation = precipitation
        )

    private fun Hourly.toPresentation(): HourlyModel =
        HourlyModel(
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
            weather = weather.map { it.toPresentation() },
            pop = pop
        )

    private fun Daily.toPresentation(): DailyModel =
        DailyModel(
            dt = dt,
            sunrise = sunrise,
            sunset = sunset,
            moonrise = moonrise,
            moonset = moonset,
            moonPhase = moonPhase,
            summary = summary,
            temp = temp.toPresentation(),
            feelsLike = feelsLike.toPresentation(),
            pressure = pressure,
            humidity = humidity,
            dewPoint = dewPoint,
            windSpeed = windSpeed,
            windDeg = windDeg,
            windGust = windGust,
            weather = weather.map { it.toPresentation() },
            clouds = clouds,
            pop = pop,
            rain = rain,
            uvi = uvi
        )

    private fun Temp.toPresentation(): TempModel=
        TempModel(
            day = day,
            min = min,
            max = max,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun FeelsLike.toPresentation(): FeelsLikeModel =
        FeelsLikeModel(
            day = day,
            night = night,
            eve = eve,
            morn = morn
        )

    private fun Alert.toPresentation(): AlertModel =
        AlertModel(
            senderName = senderName,
            event = event,
            start = start,
            end = end,
            description = description,
            tags = tags
        )
}