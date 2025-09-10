package com.samsung.weather_data.remote.mapper

import com.samsung.weather_data.remote.model.CloudCoverDto
import com.samsung.weather_data.remote.model.GetDailyAggregationDto
import com.samsung.weather_data.remote.model.HumidityDto
import com.samsung.weather_data.remote.model.PrecipitationDto
import com.samsung.weather_data.remote.model.PressureDto
import com.samsung.weather_data.remote.model.TemperatureDto
import com.samsung.weather_data.remote.model.WindDto
import com.samsung.weather_data.remote.model.WindMaxDto
import org.dosys.weather_domain.model.CloudCover
import org.dosys.weather_domain.model.DailyAggregation
import org.dosys.weather_domain.model.Humidity
import org.dosys.weather_domain.model.Location
import org.dosys.weather_domain.model.Precipitation
import org.dosys.weather_domain.model.Pressure
import org.dosys.weather_domain.model.Temperature
import org.dosys.weather_domain.model.Wind
import org.dosys.weather_domain.model.WindMax

class DailyAggregationMapper {

    fun mapToDomain(
        getDailyAggregationDto: com.samsung.weather_data.remote.model.GetDailyAggregationDto
    ): DailyAggregation {
        with (getDailyAggregationDto) {
            return DailyAggregation(
                location = Location(latitude = lat, longitude = lon),
                tz = tz,
                date = date,
                units = units,
                cloudCover = cloudCover.toDomain(),
                humidity = humidity.toDomain(),
                precipitation = precipitation.toDomain(),
                temperature = temperature.toDomain(),
                pressure = pressure.toDomain(),
                wind = wind.toDomain()
            )
        }
    }

    fun mapToData(
        dailyAggregation: DailyAggregation
    ): com.samsung.weather_data.remote.model.GetDailyAggregationDto {
        with(dailyAggregation) {
            return com.samsung.weather_data.remote.model.GetDailyAggregationDto(
                lat = location.latitude,
                lon = location.longitude,
                tz = tz,
                date = date,
                units = units,
                cloudCover = cloudCover.toData(),
                humidity = humidity.toData(),
                precipitation = precipitation.toData(),
                temperature = temperature.toData(),
                pressure = pressure.toData(),
                wind = wind.toData()
            )
        }
    }

    private fun com.samsung.weather_data.remote.model.CloudCoverDto.toDomain(): CloudCover =
        CloudCover(afternoon = afternoon)

    private fun com.samsung.weather_data.remote.model.HumidityDto.toDomain(): Humidity =
        Humidity(afternoon = afternoon)

    private fun com.samsung.weather_data.remote.model.PrecipitationDto.toDomain(): Precipitation =
        Precipitation(total = total)

    private fun com.samsung.weather_data.remote.model.TemperatureDto.toDomain(): Temperature =
        Temperature(
            min = min,
            max = max,
            afternoon = afternoon,
            night = night,
            evening = evening,
            morning = morning
        )

    private fun com.samsung.weather_data.remote.model.PressureDto.toDomain(): Pressure =
        Pressure(afternoon = afternoon)

    private fun com.samsung.weather_data.remote.model.WindDto.toDomain(): Wind =
        Wind(max = max.toDomain())

    private fun com.samsung.weather_data.remote.model.WindMaxDto.toDomain(): WindMax =
        WindMax(
            speed = speed,
            direction = direction
        )

    private fun CloudCover.toData(): com.samsung.weather_data.remote.model.CloudCoverDto =
        com.samsung.weather_data.remote.model.CloudCoverDto(afternoon = afternoon)

    private fun Humidity.toData(): com.samsung.weather_data.remote.model.HumidityDto =
        com.samsung.weather_data.remote.model.HumidityDto(afternoon = afternoon)

    private fun Precipitation.toData(): com.samsung.weather_data.remote.model.PrecipitationDto =
        com.samsung.weather_data.remote.model.PrecipitationDto(total = total)

    private fun Temperature.toData(): com.samsung.weather_data.remote.model.TemperatureDto =
        com.samsung.weather_data.remote.model.TemperatureDto(
            min = min,
            max = max,
            afternoon = afternoon,
            night = night,
            evening = evening,
            morning = morning
        )

    private fun Pressure.toData(): com.samsung.weather_data.remote.model.PressureDto =
        com.samsung.weather_data.remote.model.PressureDto(afternoon = afternoon)

    private fun Wind.toData(): com.samsung.weather_data.remote.model.WindDto =
        com.samsung.weather_data.remote.model.WindDto(max = max.toData())

    private fun WindMax.toData(): com.samsung.weather_data.remote.model.WindMaxDto =
        com.samsung.weather_data.remote.model.WindMaxDto(
            speed = speed,
            direction = direction
        )
}