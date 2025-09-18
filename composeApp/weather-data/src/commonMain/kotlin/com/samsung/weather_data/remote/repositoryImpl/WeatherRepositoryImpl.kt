package com.samsung.weather_data.remote.repositoryImpl

import com.samsung.weather_data.remote.api.GetCurrentWeatherApi
import com.samsung.weather_data.remote.api.GetWeatherFor5DaysApi
import com.samsung.weather_data.remote.mapper.CurrentWeatherMapper
import com.samsung.weather_data.remote.mapper.WeatherFor5DaysMapper
import org.dosys.weather_domain.model.CurrentWeather
import org.dosys.weather_domain.model.ExcludeType
import org.dosys.weather_domain.model.WeathersFor5Days
import org.dosys.weather_domain.repository.WeatherRepository

internal class WeatherRepositoryImpl(
    private val getCurrentWeatherApi: GetCurrentWeatherApi,
    private val getWeatherFor5DaysApi: GetWeatherFor5DaysApi,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val weatherFor5DaysMapper: WeatherFor5DaysMapper,
): WeatherRepository {

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        mode: List<ExcludeType>?,
        units: String?,
        lang: String?
    ): Result<CurrentWeather> {
        return try {
            val result = getCurrentWeatherApi.getCurrentWeather(
                lat = lat,
                lon = lon,
                mode = mode?.joinToString(", ") { it.value },
                units = units,
                lang = lang
            )
            Result.success(currentWeatherMapper.mapToDomain(result))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception(e.message))
        }
    }

    override suspend fun getWeatherFor5Days(
        lat: Double,
        lon: Double,
        units: String?,
        mode: List<ExcludeType>?,
        cnt: Int?,
        lang: String?
    ): Result<WeathersFor5Days> {
        return try {
            val result = getWeatherFor5DaysApi.getWeathersFor5Days(
                lat = lat,
                lon = lon,
                mode = mode?.joinToString(", ") { it.value },
                cnt = cnt,
                units = units,
                lang = lang
            )
            Result.success(weatherFor5DaysMapper.mapToDomain(result))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception(e.message))
        }
    }

}