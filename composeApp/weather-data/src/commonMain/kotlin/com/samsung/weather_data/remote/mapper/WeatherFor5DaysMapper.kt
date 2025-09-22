package com.samsung.weather_data.remote.mapper

import com.samsung.weather_data.remote.model.CityDto
import com.samsung.weather_data.remote.model.CloudsDto
import com.samsung.weather_data.remote.model.CoordDto
import com.samsung.weather_data.remote.model.DailyWeatherDto
import com.samsung.weather_data.remote.model.MainDto
import com.samsung.weather_data.remote.model.RainDto
import com.samsung.weather_data.remote.model.WeatherDto
import com.samsung.weather_data.remote.model.WeathersFor5DaysResponseDto
import com.samsung.weather_data.remote.model.WindDto
import org.dosys.weather_domain.model.City
import org.dosys.weather_domain.model.Clouds
import org.dosys.weather_domain.model.Coord
import org.dosys.weather_domain.model.DailyWeather
import org.dosys.weather_domain.model.Main
import org.dosys.weather_domain.model.Rain
import org.dosys.weather_domain.model.Weather
import org.dosys.weather_domain.model.WeathersFor5Days
import org.dosys.weather_domain.model.Wind

internal class WeatherFor5DaysMapper {

    fun mapToDomain(weathersFor5DaysResponseDto: WeathersFor5DaysResponseDto): WeathersFor5Days {
        return WeathersFor5Days(
            cnt = weathersFor5DaysResponseDto.cnt,
            list = weathersFor5DaysResponseDto.list.map {
                it.toDomain()
            },
            city = weathersFor5DaysResponseDto.city.toDomain()
        )
    }

    fun mapToData(weathersFor5Days: WeathersFor5Days): WeathersFor5DaysResponseDto = WeathersFor5DaysResponseDto(
        cod = 0,
        message = "",
        cnt = weathersFor5Days.cnt,
        list = weathersFor5Days.list.map {
            it.toData()
        },
        city = weathersFor5Days.city.toData()
    )

    private fun DailyWeatherDto.toDomain(): DailyWeather = DailyWeather(
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

    private fun CoordDto.toDomain(): Coord = Coord(
        lat = lat,
        lon = lon
    )

    private fun CityDto.toDomain(): City = City(
        id = id,
        name = name,
        coord = coord.toDomain(),
        country = countryCode,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

    private fun MainDto.toDomain(): Main = Main(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity,
        seaLevel = seaLevel,
        groundLevel = groundLevel
    )

    private fun WeatherDto.toDomain(): Weather = Weather(
        id = id,
        main = main,
        description = description,
        icon = icon
    )

    private fun CloudsDto.toDomain(): Clouds = Clouds(
        all = all
    )

    private fun WindDto.toDomain(): Wind = Wind(
        speed = speed,
        deg = deg,
        gust = gust
    )

    private fun DailyWeatherDto.Rain3HourDto.toDomain(): DailyWeather.Rain3Hour =
        DailyWeather.Rain3Hour(
            threeHour = threeHour
        )

    private fun DailyWeatherDto.Sys.toDomain(): DailyWeather.Sys = DailyWeather.Sys(
        pod = pod
    )

    // ============================================================================ \\

    private fun DailyWeather.toData(): DailyWeatherDto = DailyWeatherDto(
        dt = dt,
        main = main.toData(),
        weather = weather.map { it.toData() },
        clouds = clouds.toData(),
        wind = wind.toData(),
        visibility = visibility,
        pop = pop,
        rain = rain?.toData(),
        sys = sys.toData(),
        dtText = dtText
    )

    private fun Coord.toData(): CoordDto = CoordDto(
        lat = lat,
        lon = lon
    )

    private fun City.toData(): CityDto = CityDto(
        id = id,
        name = name,
        coord = coord.toData(),
        countryCode = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

    private fun Main.toData(): MainDto = MainDto(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity
    )

    private fun Weather.toData(): WeatherDto = WeatherDto(
        id = id,
        main = main,
        description = description,
        icon = icon
    )

    private fun Clouds.toData(): CloudsDto = CloudsDto(
        all = all
    )

    private fun Wind.toData(): WindDto = WindDto(
        speed = speed,
        deg = deg,
        gust = gust
    )

    private fun DailyWeather.Rain3Hour.toData(): DailyWeatherDto.Rain3HourDto = DailyWeatherDto.Rain3HourDto(
        threeHour = threeHour
    )

    private fun DailyWeather.Sys.toData(): DailyWeatherDto.Sys = DailyWeatherDto.Sys(
        pod = pod
    )
}