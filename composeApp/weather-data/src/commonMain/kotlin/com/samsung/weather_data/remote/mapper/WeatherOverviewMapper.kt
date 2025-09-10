package com.samsung.weather_data.remote.mapper

import com.samsung.weather_data.remote.model.GetWeatherOverviewDto
import org.dosys.weather_domain.model.Location
import org.dosys.weather_domain.model.WeatherOverview

class WeatherOverviewMapper {

    fun mapToDomain(
        getWeatherOverviewDto: GetWeatherOverviewDto
    ): WeatherOverview {
        with(getWeatherOverviewDto) {
            return WeatherOverview(
                location = Location(latitude = latitude, longitude = longitude),
                timezone = timezone,
                date = date,
                units = units,
                weatherOverview = weatherOverview
            )
        }
    }

    // Domain -> DTO
    fun mapToData(
        weatherOverview: WeatherOverview
    ): GetWeatherOverviewDto {
        with(weatherOverview) {
            return GetWeatherOverviewDto(
                latitude = location.latitude,
                longitude = location.longitude,
                timezone = timezone,
                date = date,
                units = units,
                weatherOverview = weatherOverview.weatherOverview
            )
        }
    }
}