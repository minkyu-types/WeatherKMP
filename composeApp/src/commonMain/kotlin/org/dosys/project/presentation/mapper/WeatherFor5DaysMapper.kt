package org.dosys.project.presentation.mapper

import org.dosys.project.presentation.feature.weather.main.CityModel
import org.dosys.project.presentation.feature.weather.main.CloudsModel
import org.dosys.project.presentation.feature.weather.main.CoordModel
import org.dosys.project.presentation.feature.weather.main.ThreeHourForecastModel
import org.dosys.project.presentation.feature.weather.main.MainModel
import org.dosys.project.presentation.feature.weather.main.WeatherModel
import org.dosys.project.presentation.feature.weather.main.WeathersFor5DaysModel
import org.dosys.project.presentation.feature.weather.main.WindModel
import org.dosys.weather_domain.model.City
import org.dosys.weather_domain.model.Clouds
import org.dosys.weather_domain.model.Coord
import org.dosys.weather_domain.model.DailyWeather
import org.dosys.weather_domain.model.Main
import org.dosys.weather_domain.model.Weather
import org.dosys.weather_domain.model.WeathersFor5Days
import org.dosys.weather_domain.model.Wind

class WeatherFor5DaysMapper {

    fun mapToPresentation(weathersFor5Days: WeathersFor5Days): WeathersFor5DaysModel {
        return WeathersFor5DaysModel(
            cnt = weathersFor5Days.cnt,
            list = weathersFor5Days.list.map {
                it.toPresentation()
            },
            city = weathersFor5Days.city.toPresentation()
        )
    }

    fun mapToDomain(weathersFor5DaysModel: WeathersFor5DaysModel): WeathersFor5Days = WeathersFor5Days(
        cnt = weathersFor5DaysModel.cnt,
        list = weathersFor5DaysModel.list.map {
            it.toDomain()
        },
        city = weathersFor5DaysModel.city.toDomain()
    )

    private fun DailyWeather.toPresentation(): ThreeHourForecastModel = ThreeHourForecastModel(
        dt = dt,
        main = main.toPresentation(),
        weather = weather.map { it.toPresentation() },
        clouds = clouds.toPresentation(),
        wind = wind.toPresentation(),
        visibility = visibility,
        pop = pop,
        rain = rain?.toPresentation(),
        sys = sys.toPresentation(),
        dtText = dtText
    )

    private fun Coord.toPresentation(): CoordModel = CoordModel(
        lat = lat,
        lon = lon
    )

    private fun City.toPresentation(): CityModel = CityModel(
        id = id,
        name = name,
        coord = coord.toPresentation(),
        country = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

    private fun Main.toPresentation(): MainModel = MainModel(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity,
        seaLevel = seaLevel,
        grndLevel = groundLevel
    )

    private fun Weather.toPresentation(): WeatherModel = WeatherModel(
        id = id,
        main = main,
        description = description,
        icon = icon
    )

    private fun Clouds.toPresentation(): CloudsModel = CloudsModel(
        all = all
    )

    private fun Wind.toPresentation(): WindModel = WindModel(
        speed = speed,
        deg = deg,
        gust = gust
    )

    private fun DailyWeather.Rain3Hour.toPresentation(): ThreeHourForecastModel.RainThreeHourModel =
        ThreeHourForecastModel.RainThreeHourModel(
            threeHour = threeHour
        )

    private fun DailyWeather.Sys.toPresentation(): ThreeHourForecastModel.SysModel = ThreeHourForecastModel.SysModel(
        pod = pod
    )

    // ============================================================================ \\

    private fun ThreeHourForecastModel.toDomain(): DailyWeather = DailyWeather(
        dt = dt,
        main = main.toDomain(),
        weather = weather.map { it.toDomain() },
        clouds = clouds.toDomain(),
        wind = wind.toDomain(),
        visibility = visibility,
        pop = pop,
        rain = rain?.toDomain(),
        sys = sys.toDomain(),
        dtText = dtText
    )

    private fun CoordModel.toDomain(): Coord = Coord(
        lat = lat,
        lon = lon
    )

    private fun CityModel.toDomain(): City = City(
        id = id,
        name = name,
        coord = coord.toDomain(),
        country = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

    private fun MainModel.toDomain(): Main = Main(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity,
        seaLevel = seaLevel,
        groundLevel = grndLevel
    )

    private fun WeatherModel.toDomain(): Weather = Weather(
        id = id,
        main = main,
        description = description,
        icon = icon
    )

    private fun CloudsModel.toDomain(): Clouds = Clouds(
        all = all
    )

    private fun WindModel.toDomain(): Wind = Wind(
        speed = speed,
        deg = deg,
        gust = gust
    )

    private fun ThreeHourForecastModel.RainThreeHourModel.toDomain(): DailyWeather.Rain3Hour = DailyWeather.Rain3Hour(
        threeHour = threeHour
    )

    private fun ThreeHourForecastModel.SysModel.toDomain(): DailyWeather.Sys = DailyWeather.Sys(
        pod = pod
    )
}